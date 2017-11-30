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
import com.dragon.wallpaperapp.mvp.model.WallpaperApiModel

/**
 * Created by D22434 on 2017/11/30.
 */

class HomeAdapter(data: List<WallpaperApiModel.SpecialBean>?) : BaseQuickAdapter<WallpaperApiModel.SpecialBean, BaseViewHolder>(R.layout.item_bander, data) {

    override fun convert(helper: BaseViewHolder, item: WallpaperApiModel.SpecialBean) {
//        helper.setText(R.id.name, item.name)
//        helper.setText(R.id.desc, item.desc)
        Log.e("TAG", item.icon)
        // 加载网络图片
        GlideApp.with(mContext)
                .load(item.icon)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transition(withCrossFade())
                .into(helper.getView<View>(R.id.iv) as ImageView)
    }
}
