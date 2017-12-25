package com.dragon.wallpaperapp.mvp.contract

import com.dragon.wallpaperapp.mvp.base.IBasePresenter
import com.dragon.wallpaperapp.mvp.base.IBaseView
import com.dragon.wallpaperapp.mvp.model.bean.FilmSection

/**
 * Created by D22434 on 2017/11/28.
 */

interface MovieContract {

    interface View : IBaseView {
        fun showMovies(datas: List<FilmSection>?)
    }

    interface Presenter : IBasePresenter<View> {
        fun getData()
    }
}
