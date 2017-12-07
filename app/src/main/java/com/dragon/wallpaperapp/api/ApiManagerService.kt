package com.dragon.wallpaperapp.api


import com.dragon.wallpaperapp.mvp.model.*
import io.reactivex.Observable
import retrofit2.http.*

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

//    http://service.aibizhi.adesk.com/v3/homepage?limit=30&skip=30&adult=false&did=867919026491418&first=0&order=hot
    //获取homepage数据?limit=30&adult=false&did=867919026491418&first=1&order=hot
    @GET("v3/homepage")
    fun getHomePageInfo(@QueryMap map: Map<String, String>): Observable<HomePageApiModel>

    //获取homepage数据
    @GET("v1/wallpaper/category?adult=false&first=1")
    fun getCategoryList(): Observable<CategoryApiModel>

    //获取homepage数据
//    @GET("v1/vertical/category/{cate_id}/vertical?limit=10&adult=false&first=1&order=new")
    @GET("v1/vertical/category/{cate_id}/vertical")
    fun getWallpaperForCate(@Path("cate_id") cate_id: String,
                            @QueryMap map: Map<String, String>): Observable<WallpaperApiModel>

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
