package com.dragon.wallpaperapp.ui.activity

import android.app.Activity
import android.os.Bundle
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.contract.MovieContract
import com.dragon.wallpaperapp.mvp.model.bean.FilmSection
import com.dragon.wallpaperapp.mvp.presenter.MoviePresenter
import kotlinx.android.synthetic.main.activity_movie.*

class MovieDetailActivity : Activity(), MovieContract.View {
    var url: String = ""

    companion object {
        var WEB_URL: String = "url"
        var TAG: String = "WebViewActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        url = intent.getStringExtra(WEB_URL)


        var mPresenter = MoviePresenter()
        mPresenter.attachView(this)
        mPresenter.getDetailData(url)
    }


    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMovies(datas: List<FilmSection>?) {
    }

    override fun showMovies(data: String) {
//        tv_show.text = Html.fromHtml(data)
        wv_show.loadDataWithBaseURL(null,data,"text/html","utf-8",null)
    }

}
