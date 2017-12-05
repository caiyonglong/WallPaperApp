package com.dragon.wallpaperapp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.contract.HomePageContract
import com.dragon.wallpaperapp.mvp.model.Wallpaper
import com.dragon.wallpaperapp.mvp.presenter.HomePagePresenter
import com.dragon.wallpaperapp.ui.adapter.HomeAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.fragment_homepage.*

/**
 * Created by D22434 on 2017/11/29.
 */

class HomePageFragment : Fragment(), HomePageContract.View {


    var mPresenter: HomePagePresenter = HomePagePresenter()

    lateinit var mAdapter: HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_homepage, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)

        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        mAdapter = HomeAdapter(null)
        recyclerView.adapter = mAdapter

        mPresenter.attachView(this)
        mPresenter.getWallpaper()
    }

    override fun showWallpaper(wallpapers: List<Wallpaper>?) {
        mAdapter.setNewData(wallpapers)
        mAdapter.notifyDataSetChanged()

    }

    override fun showError(error: String) {
        Log.e("TAG", error)
    }

}
