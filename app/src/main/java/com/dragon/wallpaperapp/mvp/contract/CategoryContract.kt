package com.dragon.wallpaperapp.mvp.contract

import com.dragon.wallpaperapp.mvp.base.IBasePresenter
import com.dragon.wallpaperapp.mvp.base.IBaseView

/**
 * Created by D22434 on 2017/11/28.
 */
object CategoryContract {

    interface View : IBaseView {
    }

    interface Presenter : IBasePresenter<View> {
    }
}