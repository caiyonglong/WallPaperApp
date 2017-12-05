package com.dragon.wallpaperapp.api


import com.dragon.wallpaperapp.mvp.model.CategoryApiModel
import com.dragon.wallpaperapp.mvp.model.HomePageApiModel
import com.dragon.wallpaperapp.mvp.model.RankingApiModel
import com.dragon.wallpaperapp.mvp.model.RecommendApiModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by D22434 on 2017/11/28.
 */

interface ApiManagerService {

    /**
     *
     * 最新
    http://service.aibizhi.adesk.com/v1/vertical/vertical?limit=30&adult=false&first=1&order=new
    主页
    http://service.aibizhi.adesk.com/v3/homepage?limit=30&adult=false&did=867919026491418&first=1&order=hot

    热门
    http://service.aibizhi.adesk.com/v3/wallpaper?limit=30&adult=false&first=1&order=hot

    分类
    http://service.aibizhi.adesk.com/v1/wallpaper/category?adult=false&first=1

    http://service.aibizhi.adesk.com/v1/vertical/category/4e4d610cdf714d2966000000/vertical?limit=10&adult=false&first=1&order=new

     */

    //获取homepage数据
    @GET("v3/homepage?limit=30&adult=false&did=867919026491418&first=1&order=hot")
    fun getHomePageInfo(): Observable<HomePageApiModel>

    //获取homepage数据
    @GET("v1/wallpaper/category?adult=false&first=1")
    fun getCategoryInfo(): Observable<CategoryApiModel>

    //获取homepage数据
    @GET("v1/vertical/category/4e4d610cdf714d2966000000/vertical?limit=10&adult=false&first=1&order=new")
    fun getWallpaperForCate(): Observable<CategoryApiModel>

    //获取爱壁纸接口
    @GET
    fun getRanking(@Url url: String): Observable<RankingApiModel>

    //获取推荐壁纸
    @GET
    fun getRecommend(@Url url: String): Observable<RecommendApiModel>

    //获取分类壁纸
    @GET
    fun getCategory(@Url url: String): Observable<CategoryApiModel>
}
