package com.dragon.wallpaperapp.ui.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.api.GlideApp
import com.dragon.wallpaperapp.mvp.model.bean.Wallpaper

/**
 * Created by D22434 on 2017/11/30.
 */

class HomeAdapter(data: List<Wallpaper>?) : BaseQuickAdapter<Wallpaper, BaseViewHolder>(R.layout.item_wallpaper, data) {

    override fun convert(helper: BaseViewHolder, item: Wallpaper) {
//        helper.setText(R.id.name, item.name)
//        helper.setText(R.id.desc, item.desc)
        Log.e("TAG", item.img)
        Log.e("TAG", item.preview)
        Log.e("TAG", item.thumb)
        Log.e("TAG", item.wp)
        // 加载网络图片
        GlideApp.with(mContext)
                .load(item.thumb)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transition(withCrossFade())
                .into(helper.getView<View>(R.id.iv) as ImageView)
    }
}
