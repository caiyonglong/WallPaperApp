package com.dragon.wallpaperapp.ui.fragment;

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.contract.RankingContract
import com.dragon.wallpaperapp.mvp.model.ApiModel
import com.dragon.wallpaperapp.mvp.model.WallpaperDetailModel
import com.dragon.wallpaperapp.mvp.presenter.RankingPresenter
import com.dragon.wallpaperapp.ui.adapter.RankingAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout


/**
 * Created by D22434 on 2017/11/28.
 */

class RankingFragment : Fragment(), RankingContract.View {


    var mPresenter: RankingPresenter = RankingPresenter()

    private lateinit var mRecyclerView: RecyclerView

    private lateinit var mAdapter: RankingAdapter

    lateinit var mSmartRefreshLayout: SmartRefreshLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_homepage, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)

        mRecyclerView = view.findViewById<View>(R.id.rv_list) as RecyclerView
        mSmartRefreshLayout = view.findViewById<View>(R.id.refreshLayout) as SmartRefreshLayout

        mRecyclerView.layoutManager = GridLayoutManager(activity, 3)
        mAdapter = RankingAdapter(null)
        mRecyclerView.adapter = mAdapter

        mPresenter.attachView(this)
        if (ApiModel.Ranking != null)
            mPresenter.getRanking(ApiModel.Ranking!!)
    }

    override fun updateView(wallpapers: List<WallpaperDetailModel>?) {
        mAdapter.setNewData(wallpapers)
        mAdapter.notifyDataSetChanged()
    }

    override fun setEmpty() {
        mAdapter.setEmptyView(R.layout.item_empty)
    }
}