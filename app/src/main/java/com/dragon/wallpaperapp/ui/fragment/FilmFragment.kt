package com.dragon.wallpaperapp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.contract.MovieContract
import com.dragon.wallpaperapp.mvp.model.bean.CategoryFilm
import com.dragon.wallpaperapp.mvp.presenter.MoviePresenter
import kotlinx.android.synthetic.main.fragment_film.*
import java.util.*


/**
 * Created by D22434 on 2017/11/29.
 */

class FilmFragment : Fragment(), MovieContract.View {


    var fragments: ArrayList<Fragment> = arrayListOf()

    var titles = arrayOf("2017新片精品", "2017必看热片", "迅雷电影资源")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        var mPresenter = MoviePresenter()
        mPresenter.attachView(this)
    }

    override fun hideLoading() {

    }

    override fun showMovies(lists: List<CategoryFilm>?) {
        if (lists != null) {
            for (data in lists) {
                tv.append("<a href=\"${data.url}\">${data.title}</a>")
                for (list in data.datas) {
                    tv.append("<a href=\"${list.url}\">${list.title}</a>  ${list.date}")
                }
            }
        }

    }

    override fun showLoading() {

    }
}
