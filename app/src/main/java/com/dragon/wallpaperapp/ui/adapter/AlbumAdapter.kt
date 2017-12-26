package com.dragon.wallpaperapp.ui.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.api.GlideApp
import com.dragon.wallpaperapp.mvp.model.bean.Album

/**
 * Created by D22434 on 2017/11/30.
 */

class AlbumAdapter(data: List<Album>?) : BaseQuickAdapter<Album, BaseViewHolder>(R.layout.item_album, data) {

    override fun convert(helper: BaseViewHolder, item: Album) {
//        helper.setText(R.id.name, item.name)
//        helper.setText(R.id.desc, item.desc)
        Log.e("TAG", item.toString())
        // 加载网络图片
        GlideApp.with(mContext)
                .load(item.cover)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transition(withCrossFade())
                .into(helper.getView<View>(R.id.iv) as ImageView)

        helper.getView<TextView>(R.id.name).text = item.name
        helper.getView<TextView>(R.id.desc).text = item.desc
    }
}
