package com.dragon.wallpaperapp.mvp.presenter

import com.dragon.wallpaperapp.api.ApiManager
import com.dragon.wallpaperapp.mvp.contract.HomePageContract
import com.dragon.wallpaperapp.mvp.contract.WallpaperContract
import com.dragon.wallpaperapp.mvp.model.HomePageApiModel
import com.dragon.wallpaperapp.mvp.model.WallpaperApiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by D22434 on 2017/11/28.
 */

class WallpaperPresenter : WallpaperContract.Presenter {

    lateinit var mView: WallpaperContract.View

    override fun subscribe() {
    }

    override fun unsubscribe() {
    }

    override fun attachView(view: WallpaperContract.View) {
        mView = view
    }

    override fun detachView() {
    }

    override fun getWallpaperForCate(cate_id: String, limit: Int, skip: Int, order: String) {
        var map: Map<String, Any> = mapOf("limit" to limit,
                "skip" to skip,
                "order" to order,
                "adult" to "false",
                "first" to "0")
        ApiManager.instance
                .apiService
                .getWallpaperForCate(cate_id, map as Map<String, String>)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: WallpaperApiModel ->
                    mView.showWallpaper(t.res?.vertical)
                }, { e: Throwable ->
                    mView.showError(e.message!!)
                })
    }

    override fun getWallpaper(limit: Int, skip: Int, order: String) {
        var map: Map<String, Any> = mapOf("limit" to limit,
                "skip" to skip,
                "order" to order,
                "adult" to "false",
                "first" to "0")
        ApiManager.instance
                .apiService
                .getWallpaper(map as Map<String, String>)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: WallpaperApiModel ->
                    mView.showWallpaper(t.res?.vertical)
                }, { e: Throwable ->
                    mView.showError(e.message!!)
                })
    }

}



