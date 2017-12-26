package com.dragon.wallpaperapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.contract.MovieContract
import com.dragon.wallpaperapp.mvp.model.bean.FilmSection
import com.dragon.wallpaperapp.mvp.presenter.MoviePresenter
import com.dragon.wallpaperapp.ui.activity.MovieDetailActivity
import com.dragon.wallpaperapp.ui.adapter.SectionAdapter
import kotlinx.android.synthetic.main.fragment_recyclerview.*


/**
 * Created by D22434 on 2017/11/29.
 */

class FilmFragment : Fragment(), MovieContract.View {

    var mPresenter = MoviePresenter()
    var mAdapter = SectionAdapter(R.layout.item_movie, R.layout.item_header, null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        mPresenter.attachView(this)
        init()
    }

    private fun init() {
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
        recyclerView.adapter = mAdapter
        mPresenter.getData()
        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            // 在这里可以做任何自己想要的处理
            val intent = Intent(context, MovieDetailActivity::class.java)
            var data = adapter.getItem(position) as FilmSection
            if (data.isHeader) {
                intent.putExtra(MovieDetailActivity.WEB_URL, data.url)
            } else {
                intent.putExtra(MovieDetailActivity.WEB_URL, data.flimData?.url)
            }
            context.startActivity(intent)
        }
    }

    override fun hideLoading() {

    }

    override fun showMovies(lists: List<FilmSection>?) {
        println("result =  " + lists?.size + lists.toString())
        mAdapter.setNewData(lists)
        mAdapter.notifyDataSetChanged()
    }

    override fun showLoading() {

    }
    override fun showMovies(datas: String) {

    }

}
