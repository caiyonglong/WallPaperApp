package com.dragon.wallpaperapp.ui.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.api.GlideApp
import com.dragon.wallpaperapp.mvp.model.Wallpaper


/**
 * Created by yonglong on 2017/12/8.
 */
class ImageAdapter(data: List<Wallpaper>?) : BaseQuickAdapter<Wallpaper, BaseViewHolder>(R.layout.wp_image, data) {

    var checkedID: Int = 0

    override fun convert(helper: BaseViewHolder, item: Wallpaper) {
        Log.e("TAG", "position:" + helper.adapterPosition + "checkedID = " + checkedID)
        // 加载网络图片
        GlideApp.with(mContext)
                .load(item.thumb)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(helper.getView<ImageView>(R.id.preview))

        if (helper.adapterPosition == checkedID) {
            Log.e("TAG", "position:" + helper.adapterPosition+ "-----")
            helper.getView<ImageView>(R.id.iv_check).visibility = View.VISIBLE
        } else {
            helper.getView<ImageView>(R.id.iv_check).visibility = View.INVISIBLE
        }

    }

}