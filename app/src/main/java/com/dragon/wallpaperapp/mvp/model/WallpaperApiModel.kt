package com.dragon.wallpaperapp.mvp.model

import com.dragon.wallpaperapp.mvp.model.bean.Wallpaper

/**
 * Created by D22434 on 2017/12/5.
 */

class WallpaperApiModel(var msg: String? = null, var res: ResBean? = null) {
    class ResBean {
        var vertical: List<Wallpaper>? = null
    }
}
