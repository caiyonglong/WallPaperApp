package com.dragon.wallpaperapp.mvp.model

/**
 * Created by D22434 on 2017/12/18.
 */
class Banner {
    var id: String = ""
    var type: Int = 0
    var thumb: String = ""
    var lcover: String = ""
    var cover: String = ""
    var name: String = ""
    var desc: String = ""
    override fun toString(): String {
        return "Banner(id='$id', type=$type, thumb='$thumb', lcover='$lcover', cover='$cover', name='$name', desc='$desc')"
    }


}