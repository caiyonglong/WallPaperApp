package com.dragon.wallpaperapp.ui.adapter

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.api.GlideApp
import com.dragon.wallpaperapp.mvp.model.bean.Category

/**
 * Created by D22434 on 2017/11/30.
 */

class CategoryAdapter(data: List<Category>?) : BaseQuickAdapter<Category, BaseViewHolder>(R.layout.item_category, data) {

    override fun convert(helper: BaseViewHolder, item: Category) {
        helper.setText(R.id.tv_category, item.name)
        // 加载网络图片
        GlideApp.with(mContext)
                .load(item.cover)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transition(withCrossFade())
                .into(helper.getView<View>(R.id.iv) as ImageView)
    }
}
