package com.dragon.wallpaperapp.ui.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dragon.wallpaperapp.R

/**
 * Created by D22434 on 2017/12/26.
 */
class DownloadAdapter(data: ArrayList<String>?) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_download, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.getView<TextView>(R.id.tv_link).text = item
    }
}
