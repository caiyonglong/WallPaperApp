package com.dragon.wallpaperapp.ui.adapter

import android.annotation.SuppressLint
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.model.WallpaperApiModel

/**
 * Created by D22434 on 2017/11/29.
 */
class QuickAdapter(bander: List<WallpaperApiModel.SpecialBean>) : BaseQuickAdapter<WallpaperApiModel.SpecialBean, BaseViewHolder>(R.layout.item_wallpaper) {

    @SuppressLint("CheckResult")
    override fun convert(helper: BaseViewHolder, item: WallpaperApiModel.SpecialBean) {
        helper.setText(R.id.tv_prompt, item.name)
        Glide.with(mContext)
                .load(helper.getView(R.id.iv))

    }


}