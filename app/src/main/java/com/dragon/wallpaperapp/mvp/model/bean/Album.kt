package com.dragon.wallpaperapp.mvp.model.bean

/**
 * Created by yonglong on 2017/12/5.
 */
class Album {
    var id: String? = ""
    var name: String? = null
    var desc: String? = null
    var cover: String? = null
    var lover: String? = null
    var atime: String? = null
    var favs: Int? = 0
    var sn: Int? = 0

    override fun toString(): String {
        return "Album(id=$id, name=$name, desc=$desc, cover=$cover, lover=$lover, atime=$atime, favs=$favs, sn=$sn)"
    }

}