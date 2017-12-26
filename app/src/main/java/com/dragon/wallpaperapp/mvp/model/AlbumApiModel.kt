package com.dragon.wallpaperapp.mvp.model

import com.dragon.wallpaperapp.mvp.model.bean.Album
import com.dragon.wallpaperapp.mvp.model.bean.Wallpaper

/**
 * Created by D22434 on 2017/12/5.
 */

class AlbumApiModel(var banner: List<ItemsBean>? = null, var album: List<Album>? = null, var wallpaper: List<Wallpaper>? = null) {

    class ItemsBean {
        var value: ValueBean? = null
        var target: String? = null
        var thumb: String? = null
        var type: String? = null
        var id: String? = null

        class ValueBean {

            var name: String? = null
            var cover: String? = null
            var id: String? = null
            var lcover: String? = null
            var desc: String? = null
            override fun toString(): String {
                return "ValueBean(name=$name, cover=$cover, id=$id, lcover=$lcover, desc=$desc)"
            }

        }

        override fun toString(): String {
            return "ItemsBean(value=$value, target=$target, thumb=$thumb)"
        }
    }

    override fun toString(): String {
        return "AlbumApiModel(banner=$banner, album=$album)"
    }

}
