package com.dragon.wallpaperapp.mvp.base

/**
 * @author yyx
 */

interface IBasePresenter<V : IBaseView> {
    fun subscribe()

    fun unsubscribe()

    fun attachView(view: V)

    fun detachView()
}
