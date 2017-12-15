package com.dragon.wallpaperapp.mvp.presenter

import com.dragon.wallpaperapp.api.ApiManager
import com.dragon.wallpaperapp.mvp.model.HomePageApiModel
import io.reactivex.schedulers.Schedulers
import org.junit.Test

/**
 * Created by D22434 on 2017/12/14.
 */
class HomePagePresenterTest {


    @Test
    fun getWallpaper() {

//        val manager = mock(HomePagePresenter::class.java)
        var map: Map<String, Any> = mapOf("limit" to 1,
                "skip" to 0,
                "order" to "hot",
                "adult" to "false",
                "first" to "0")
//        var manager = mock(HomePagePresenter::class.java)
////
//       var x: HomePagePresenter = HomePagePresenter()
//        x.getWallpaper(30,0,"hot")

        System.out.println(map.toString())
        ApiManager.instance
                .apiService
                .getHomePageInfo(map as Map<String, String>)
                .subscribeOn(Schedulers.io())
//                .observeOn(Scheduler.Worker)
                .subscribe({ t: HomePageApiModel ->
                    System.out.println(t.res.toString())
                }, { e: Throwable ->
                    System.out.println(e.message+"---")
                })
    }

}