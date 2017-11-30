package com.dragon.wallpaperapp.ui.fragment;

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.contract.HomePageContract
import com.dragon.wallpaperapp.mvp.model.WallpaperApiModel
import com.dragon.wallpaperapp.mvp.presenter.HomePagePresenter
import com.dragon.wallpaperapp.ui.adapter.CategoryAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout


/**
 * Created by D22434 on 2017/11/28.
 */

class CategoryFragment : Fragment(), HomePageContract.View {


    var mPresenter: HomePagePresenter = HomePagePresenter()

    private lateinit var mRecyclerView: RecyclerView

    private lateinit var mAdapter: CategoryAdapter

    lateinit var mSmartRefreshLayout: SmartRefreshLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_homepage, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)

        mRecyclerView = view.findViewById<View>(R.id.rv_list) as RecyclerView
        mSmartRefreshLayout = view.findViewById<View>(R.id.refreshLayout) as SmartRefreshLayout

        mRecyclerView.layoutManager = GridLayoutManager(activity, 3)
        mAdapter = CategoryAdapter(null)
        mRecyclerView.adapter = mAdapter

        mPresenter.attachView(this)
        mPresenter.getWallpaper()
    }

    override fun showEveryDay(categoryList: List<WallpaperApiModel.EverydayBean>) {
        Log.e("TAG", categoryList.toString())
    }

    override fun showSpecial(bander: List<WallpaperApiModel.SpecialBean>) {
    }

    override fun showCategory(categoryList: List<WallpaperApiModel.CategoryBean>) {
        mAdapter!!.setNewData(categoryList)
        mAdapter.notifyDataSetChanged()
    }

    override fun showError(error: String) {
        Log.e("TAG", error)
    }

}
