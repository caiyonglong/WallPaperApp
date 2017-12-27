package com.dragon.wallpaperapp.ui.adapter

import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.TextView
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.model.bean.FilmSection

/**
 * Created by D22434 on 2017/12/25.
 */
class SectionAdapter(layoutResId: Int, sectionHeadResId: Int, data: MutableList<FilmSection>?) : BaseSectionQuickAdapter<FilmSection, BaseViewHolder>(layoutResId, sectionHeadResId, data) {
    override fun convert(helper: BaseViewHolder, item: FilmSection) {
        var data = item.t
        helper.setText(R.id.tv_title, data.title)
        helper.setText(R.id.tv_date, data.date)
    }

    override fun convertHead(helper: BaseViewHolder, item: FilmSection) {
        helper.setText(R.id.tv_tab_title, item.header)
    }

}