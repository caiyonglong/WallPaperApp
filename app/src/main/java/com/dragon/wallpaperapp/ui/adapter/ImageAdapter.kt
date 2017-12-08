package com.dragon.wallpaperapp.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


/**
 * Created by yonglong on 2017/12/8.
 */
class ImageAdapter(private val context: Context, private val strDrawables: List<ImageView>) : PagerAdapter() {

    override fun getCount(): Int {
        return strDrawables.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        val imageView = strDrawables[position]
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}