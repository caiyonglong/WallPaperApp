package com.dragon.wallpaperapp.mvp.presenter

import org.junit.Test

/**
 * Created by D22434 on 2017/12/14.
 */
class HomePagePresenterTest {


    @Test
    fun getWallpaper() {

        var map: Map<String, Any> = mapOf(
                "limit" to 30,
                "skip" to 0,
                "order" to "hot",
                "adult" to "false",
                "first" to "0"
        )

        println(map.toString())
        println("----")
//        ApiManager.instance
//                .apiService
//                .getAlbumList(map as Map<String, String>)
//                .subscribeOn(Schedulers.io())
//                .subscribe({ t: ApiModel<AlbumApiModel> ->
//                    System.out.println(map.toString())
//                    println(t.msg + "----")
//                    println(t.res.toString())
//                }, { e: Throwable ->
//                    println(e.message)
//                })
    }

}