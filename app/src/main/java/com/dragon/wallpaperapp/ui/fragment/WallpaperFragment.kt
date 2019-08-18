package com.dragon.wallpaperapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.contract.WallpaperContract
import com.dragon.wallpaperapp.mvp.model.bean.Wallpaper
import com.dragon.wallpaperapp.mvp.presenter.WallpaperPresenter
import com.dragon.wallpaperapp.ui.activity.WallpaperDisplayActivity
import com.dragon.wallpaperapp.ui.adapter.HomeAdapter
import com.mingle.widget.LoadingView
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_recyclerview.*
import java.util.*

/**
 * Created by D22434 on 2017/11/29.
 */

class WallpaperFragment : androidx.fragment.app.Fragment(), WallpaperContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    private var mPresenter: WallpaperPresenter = WallpaperPresenter()
    private lateinit var mAdapter: HomeAdapter
    private var limit = 30
    private var skip = 0
    private var mNextPage = 1
    private var mCurrentCounter = 0
    private var isErr = true

    private var type: String = ""
    private var id: String = ""
    private var order = ""

    companion object {
        fun newInstance(id: String, order: String, type: String): WallpaperFragment {
            val args = Bundle()
            args.putString("id", id)
            args.putString("order", order)
            args.putString("type", type)
            val fragment = WallpaperFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)

        initData()
        initAdapter()
        loadData()
    }

    private fun initAdapter() {
        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        mAdapter = HomeAdapter(null)
        mAdapter.bindToRecyclerView(recyclerView)
        mAdapter.setEmptyView(R.layout.item_empty)
        mAdapter.setOnLoadMoreListener(this, recyclerView)
        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            val mWallpapers: List<Wallpaper> = mAdapter.data
            val intent = Intent(activity, WallpaperDisplayActivity::class.java)
            intent.putExtra("position", position)
            intent.putParcelableArrayListExtra("wallpapers", mWallpapers as ArrayList<out Parcelable>?)
            startActivity(intent)
        }
    }

    private fun initData() {
        type = arguments?.get("type").toString()
        id = arguments?.get("id").toString()
        order = arguments?.get("order").toString()
        mPresenter.attachView(this)
    }

    private fun loadData() {
        Log.e("loadData", "limit=${limit}skip=$skip order=$order")
        when (type) {
            "new" -> mPresenter.getWallpaper(limit, skip, order)
            "category" -> mPresenter.getWallpaperForCate(id, limit, skip, order)
            "album" -> mPresenter.getWallpaperForAlbum(id, limit, skip, order)
        }
    }

    override fun onLoadMoreRequested() {
        recyclerView.postDelayed({
            Log.e("loading ", "${mAdapter.isLoading}-----")
            if (mCurrentCounter < limit) {
                mAdapter.loadMoreEnd(true)
            } else {
                if (isErr) {
                    skip = (mNextPage - 1) * limit
                    loadData()
                    mAdapter.loadMoreComplete()
                } else {
                    isErr = true
                    mAdapter.loadMoreFail()
                }
            }
        }, 1000)
    }

    override fun showWallpaper(wallpapers: List<Wallpaper>?) {
        Log.e("showWallpaper", "----- $skip ---- $mNextPage")
        mCurrentCounter = wallpapers?.size ?: 0
        if (mNextPage == 1) {
            mAdapter.setNewData(wallpapers)
            mAdapter.disableLoadMoreIfNotFullPage(recyclerView)
            mNextPage++
        } else {
            if (mCurrentCounter > 0) {
                mAdapter.addData(wallpapers!!)
                mNextPage++
            }
        }
    }

    override fun showError(error: String) {
        mAdapter.emptyView.findViewById<LoadingView>(R.id.loading_view).setLoadingText(context?.getText(R.string.load_error))
        Logger.d(error)
    }

    override fun showLoading() {
        mAdapter.emptyView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        mAdapter.emptyView.visibility = View.INVISIBLE
    }
}
