package com.dragon.wallpaperapp.mvp.presenter

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

                    var bannerlist = mutableListOf<Banner>()
                    var k = 0
                    if (t.res?.banner != null) {
                        for (item in t.res?.banner!!) {
                            if (item.value?.name != null) {
                                val banner = Banner()
                                banner.name = item.value?.name!!
                                banner.id = item.value?.id!!
                                banner.lcover = item.value?.lcover!!
                                banner.cover = item.value?.cover!!
                                banner.name = item.value?.name!!
                                banner.desc = item.value?.desc!!
                                banner.thumb = item.thumb!!
                                bannerlist.add(banner)
                            }
                        }
                    }
//                    Logger.e(bannerlist.toString())
                    mView.showBanners(bannerlist)
                }, { e: Throwable ->
                    mView.showError(e.message!!)
                })
    }


}



