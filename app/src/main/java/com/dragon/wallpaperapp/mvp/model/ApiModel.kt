package com.dragon.wallpaperapp.mvp.model

import java.io.Serializable


/**
 * Created by D22434 on 2017/12/5.
 */

class ApiModel<T>(var msg: String? = null, var res: T? = null) : Serializable {
    override fun toString(): String {
        return "ApiModel(msg=$msg, res=$res)"
    }
}
