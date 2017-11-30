package com.dragon.wallpaperapp.mvp.contract

import com.dragon.wallpaperapp.mvp.base.IBasePresenter
import com.dragon.wallpaperapp.mvp.base.IBaseView
import com.dragon.wallpaperapp.mvp.model.WallpaperDetailModel

/**
 * Created by D22434 on 2017/11/28.
 */

interface RankingContract {

    interface View : IBaseView {
        fun updateView(wallpapers: List<WallpaperDetailModel>?)
        fun setEmpty()
    }

    interface Presenter : IBasePresenter<View> {
        fun getRanking(url: String)
    }
}
