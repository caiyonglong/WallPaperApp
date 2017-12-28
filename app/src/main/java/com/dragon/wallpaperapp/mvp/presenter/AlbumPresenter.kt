package com.dragon.wallpaperapp.mvp.presenter

import android.util.Log
import com.dragon.wallpaperapp.api.ApiManager
import com.dragon.wallpaperapp.mvp.contract.AlbumContract
import com.dragon.wallpaperapp.mvp.model.AlbumApiModel
import com.dragon.wallpaperapp.mvp.model.ApiModel
import com.dragon.wallpaperapp.mvp.model.Banner
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by D22434 on 2017/11/28.
 */

class AlbumPresenter : AlbumContract.Presenter {

    lateinit var mView: AlbumContract.View

    override fun subscribe() {
    }

    override fun unsubscribe() {
    }

    override fun attachView(view: AlbumContract.View) {
        mView = view
    }

    override fun detachView() {
    }

    override fun getAlbums(limit: Int, skip: Int, order: String) {
        var map: Map<String, Any> = mapOf("limit" to limit,
                "skip" to skip,
                "order" to order,
                "adult" to "false",
                "first" to "0")
//        Log.e("TAG", map.toString() + "_-")
        ApiManager.instance
                .apiService
                .getAlbumList(map as Map<String, String>)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: ApiModel<AlbumApiModel> ->
                    mView.showAlbum(t.res?.album)
                    if (skip == 0) {
                        val banners = mutableListOf<Banner>()
                        Log.e("showBanners", t.res.toString())
                        val banner = t.res?.banner
                        if (banner != null) {
                            for (item in banner) {
                                val detail = Banner()
                                detail.name = item.value?.name.toString()
                                detail.id = item.value?.id.toString()
                                detail.lcover = item.value?.lcover.toString()
                                detail.cover = item.value?.cover.toString()
                                detail.name = item.value?.name.toString()
                                detail.desc = item.value?.desc.toString()
                                detail.thumb = item.thumb.toString()
                                banners.add(detail)
                            }
                            mView.showBanners(banners)
                        }
                    }
                }, { e: Throwable ->
                    mView.showError(e.message!!)
                })
    }


}



