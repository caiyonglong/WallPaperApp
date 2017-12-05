package com.dragon.wallpaperapp.mvp.model

/**
 * Created by D22434 on 2017/12/5.
 */
class HotApiModel {

    var msg: String? = null
    var res: ResBean? = null

    class ResBean {
        var wallpaper: List<Wallpaper>? = null
    }
}