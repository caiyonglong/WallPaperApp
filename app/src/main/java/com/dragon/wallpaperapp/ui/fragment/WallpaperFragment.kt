package com.dragon.wallpaperapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.contract.WallpaperContract
import com.dragon.wallpaperapp.mvp.model.Wallpaper
import com.dragon.wallpaperapp.mvp.presenter.WallpaperPresenter
import com.dragon.wallpaperapp.ui.activity.WallpaperDisplayActivity
import com.dragon.wallpaperapp.ui.adapter.HomeAdapter
import kotlinx.android.synthetic.main.fragment_homepage.*
import java.util.*

/**
 * Created by D22434 on 2017/11/29.
 */

class WallpaperFragment : Fragment(), WallpaperContract.View {

    var mPresenter: WallpaperPresenter = WallpaperPresenter()

    lateinit var mAdapter: HomeAdapter

    companion object {
        fun newInstance(id: String, order: String): WallpaperFragment {
            val args: Bundle = Bundle()
            args.putString("cate_id", id)
            args.putString("order", order)
            val fragment = WallpaperFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_homepage, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)

        Log.e("TAG", arguments.getString("cate_id").toString() + "---")
        recyclerView.layoutManager = GridLayoutManager(activity, 3) as RecyclerView.LayoutManager?
        mAdapter = HomeAdapter(null)
        recyclerView.adapter = mAdapter

        mPresenter.attachView(this)
        mPresenter.getWallpaper(arguments.getString("cate_id"), 30, 0, arguments.getString("order"))

        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            val mWallpapers: List<Wallpaper> = mAdapter.data
            val intent = Intent(activity, WallpaperDisplayActivity::class.java)
            intent.putExtra("position", position)
            intent.putParcelableArrayListExtra("wallpapers", mWallpapers as ArrayList<out Parcelable>?)
            startActivity(intent)
        }
    }

    override fun showWallpaper(wallpapers: List<Wallpaper>?) {
        mAdapter.setNewData(wallpapers)
        mAdapter.notifyDataSetChanged()

    }

    override fun showError(error: String) {
        Log.e("TAG", error)
    }

}
