package com.dragon.wallpaperapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.contract.HomePageContract
import com.dragon.wallpaperapp.mvp.model.Wallpaper
import com.dragon.wallpaperapp.mvp.presenter.HomePagePresenter
import com.dragon.wallpaperapp.ui.activity.WallpaperDisplayActivity
import com.dragon.wallpaperapp.ui.adapter.HomeAdapter
import kotlinx.android.synthetic.main.fragment_homepage.*
import java.util.*

/**
 * Created by D22434 on 2017/11/29.
 */

class HomePageFragment : Fragment(), HomePageContract.View {


    var mPresenter: HomePagePresenter = HomePagePresenter()

    lateinit var mAdapter: HomeAdapter

    var mCurrentCounter = 30
    var TOTAL_COUNTER = 300
    var isErr = false

    companion object {
        fun newInstance(order: String): HomePageFragment {
            val args: Bundle = Bundle()
            args.putString("order", order)
            val fragment = HomePageFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_homepage, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)

        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        mAdapter = HomeAdapter(null)
        recyclerView.adapter = mAdapter

        mPresenter.attachView(this)
        mPresenter.getWallpaper(30, 0, arguments.getString("order"))

        mAdapter.disableLoadMoreIfNotFullPage(recyclerView)
        mAdapter.setEnableLoadMore(true)
        mAdapter.isUpFetchEnable = false
        mAdapter.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
            recyclerView.postDelayed(Runnable {
                if (mCurrentCounter >= TOTAL_COUNTER) {
                    //Data are all loaded.
                    mAdapter.loadMoreEnd()
                } else {
                    if (isErr) {
                        //Successfully get more data
                        mPresenter.getWallpaper(30, mCurrentCounter, "hot")
                        mCurrentCounter = mAdapter.data.size
                        mAdapter.loadMoreComplete()
                    } else {
                        //Get more data failed
                        isErr = true
                        mAdapter.loadMoreFail()

                    }
                }
            }, 100)
        }, recyclerView)


        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            val mWallpapers: List<Wallpaper> = mAdapter.data
            val intent = Intent(activity, WallpaperDisplayActivity::class.java)
            intent.putExtra("position", position)
            intent.putParcelableArrayListExtra("wallpapers", mWallpapers as ArrayList<out Parcelable>?)
            startActivity(intent)
        }
    }

    override fun showWallpaper(wallpapers: List<Wallpaper>?) {
        wallpapers?.let { mAdapter.data.addAll(it) }
        mAdapter.notifyDataSetChanged()

    }

    override fun showError(error: String) {
        Log.e("TAG", error)
    }

}
