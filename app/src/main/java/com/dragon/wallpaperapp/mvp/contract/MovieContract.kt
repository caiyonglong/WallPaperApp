package com.dragon.wallpaperapp.mvp.contract

import com.dragon.wallpaperapp.mvp.base.IBasePresenter
import com.dragon.wallpaperapp.mvp.base.IBaseView
import com.dragon.wallpaperapp.mvp.model.Banner
import com.dragon.wallpaperapp.mvp.model.bean.CategoryFilm
import com.dragon.wallpaperapp.mvp.model.bean.Wallpaper

/**
 * Created by D22434 on 2017/11/28.
 */

interface MovieContract {

    interface View : IBaseView {
        fun showMovies(datas: List<CategoryFilm>?)
    }

    interface Presenter : IBasePresenter<View> {
        fun getData()
    }
}
