package com.dragon.wallpaperapp.api


import com.dragon.wallpaperapp.mvp.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path
import retrofit2.http.QueryMap

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
    album
    http://service.aibizhi.adesk.com/v1/wallpaper/album/50b2e4de0a2ae035e5d7139b/wallpaper?limit=30&adult=false&first=1&order=new
    热门
    http://service.aibizhi.adesk.com/v3/wallpaper?limit=30&adult=false&first=1&order=hot

    分类
    http://service.aibizhi.adesk.com/v1/wallpaper/category?adult=false&first=1

    http://service.aibizhi.adesk.com/v1/vertical/category/4e4d610cdf714d2966000000/vertical?limit=10&adult=false&first=1&order=new
     */

//    http://service.aibizhi.adesk.com/v3/homepage?limit=30&skip=30&adult=false&did=867919026491418&first=0&order=hot
    //获取homepage数据?limit=30&adult=false&did=867919026491418&first=1&order=hot
    @GET("v3/homepage")
    fun getHomePageInfo(@QueryMap map: Map<String, String>): Observable<ApiModel<HomePageApiModel>>

    //最新
    @GET("v1/vertical/vertical")
    fun getWallpaper(@QueryMap map: Map<String, String>): Observable<ApiModel<WallpaperApiModel>>

    //album
    @GET("v1/wallpaper/album/{album_id}/wallpaper")
    fun getWallpaperForAlbum(@HeaderMap header: Map<String, String>, @Path("album_id") album_id: String,
                             @QueryMap map: Map<String, String>): Observable<ApiModel<AlbumApiModel>>

    //获取album数据
    @GET("v1/wallpaper/album")
    fun getAlbumList(@QueryMap map: Map<String, String>): Observable<ApiModel<AlbumApiModel>>


    //获取homepage数据
    @GET("v1/wallpaper/category?adult=false&first=1")
    fun getCategoryList(): Observable<ApiModel<CategoryApiModel>>

    //获取类别数据
    @GET("v1/vertical/category/{cate_id}/vertical")
    fun getWallpaperForCate(@Path("cate_id") cate_id: String,
                            @QueryMap map: Map<String, String>): Observable<ApiModel<WallpaperApiModel>>

}
