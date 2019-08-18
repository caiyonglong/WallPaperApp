package com.dragon.wallpaperapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.contract.MovieContract
import com.dragon.wallpaperapp.mvp.model.bean.FilmSection
import com.dragon.wallpaperapp.mvp.model.bean.Movie
import com.dragon.wallpaperapp.mvp.presenter.MoviePresenter
import com.dragon.wallpaperapp.ui.activity.MovieDetailActivity
import com.dragon.wallpaperapp.ui.adapter.SectionAdapter
import kotlinx.android.synthetic.main.fragment_movie.*


/**
 * Created by D22434 on 2017/11/29.
 */

class MovieFragment : androidx.fragment.app.Fragment(), MovieContract.View {

    var mPresenter = MoviePresenter()
    var mAdapter = SectionAdapter(R.layout.item_movie, R.layout.item_header, null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        init()
        mPresenter.attachView(this)
        mPresenter.getData()
    }

    private fun init() {
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        mAdapter.bindToRecyclerView(recyclerView)
        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, _, position ->
            // 在这里可以做任何自己想要的处理
            var data = adapter.getItem(position) as FilmSection
            if (!data.isHeader) {
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtra(MovieDetailActivity.WEB_URL, data.flimData?.url)
                intent.putExtra(MovieDetailActivity.NAME, data.flimData?.title)
                context?.startActivity(intent)
            }
        }
    }

    override fun showMovies(data: List<FilmSection>?) {
        println("result =  " + data?.size + data.toString())
        mAdapter.setNewData(data)
    }

    override fun showError(error: String) {
        loading_view.setLoadingText(error)
        Log.e("TAG", error)
    }

    override fun showLoading() {
        loading_view.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading_view.visibility = View.INVISIBLE
    }

    override fun showMovies(data: Movie) {

    }
}
