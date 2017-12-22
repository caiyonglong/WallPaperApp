package com.dragon.wallpaperapp.mvp.model.bean

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * Created by yonglong on 2017/12/23.
 */
class TabEntity(var title: String, private var selectedIcon: Int, private var unSelectedIcon: Int) : CustomTabEntity {

    override fun getTabTitle(): String {
        return title
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }
}