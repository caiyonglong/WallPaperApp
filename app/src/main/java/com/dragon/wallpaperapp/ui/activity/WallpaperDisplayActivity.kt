package com.dragon.wallpaperapp.ui.activity

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.api.GlideApp
import com.dragon.wallpaperapp.mvp.contract.WallpaperDisplayContract
import com.dragon.wallpaperapp.mvp.model.bean.Wallpaper
import com.dragon.wallpaperapp.mvp.presenter.WallpaperDisplayPresenter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_wallpaper.*

/**
 * Created by D22434 on 2017/12/6.
 */
class WallpaperDisplayActivity : AppCompatActivity(), WallpaperDisplayContract.View {

    private var mPresenter: WallpaperDisplayPresenter = WallpaperDisplayPresenter()
    private var mWallpapers = ArrayList<Wallpaper>()
    private var mPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        initData()
        initViewPager()
        initListener()
        mPresenter.loadData(mWallpapers, mPosition)

    }

    private fun initViewPager() {
        viewPager.offscreenPageLimit = 4
        viewPager.adapter = MyPageAdapter(this)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                mPosition = position
            }

        })
        viewPager.currentItem = mPosition
    }


    private fun initListener() {
        btn_set_wallpaper.setOnClickListener { _ ->
            AlertDialog.Builder(this)
                    .setItems(R.array.which_wallpaper_options, { _, which ->
                        mPresenter.saveWallpaper(mWallpapers[mPosition].wp!!, which)
                    })
                    .create()
                    .show()
        }
    }

    private fun initData() {
        mWallpapers = intent.getParcelableArrayListExtra("wallpapers")
        mPosition = intent.getIntExtra("position", -1)
        Logger.d("mWallpaper = " + mWallpapers.size + "poi = " + mPosition)
        //没有数据直接返回
        if (mPosition == -1) {
            finish()
        }
        mPresenter.attachView(this)
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }

    override fun preViewWallpaper(mBitmap: Bitmap) {
    }

    inner class MyPageAdapter(var context: Context? = null) : PagerAdapter() {

        override fun getCount(): Int {
            return mWallpapers.size
        }

        override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
            container!!.removeView(`object` as View)
        }

        override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
            return view == `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): View? {
            val view = getView(position, container)
            container.addView(view)
            return view
        }


        private fun getView(position: Int, container: ViewGroup?): View? {
            // create holder
            val holder = LayoutInflater.from(context).inflate(R.layout.wp_image, null)
            // create View
            if (mWallpapers.isNotEmpty()) {
                GlideApp.with(context)
                        .load(mWallpapers[position].img)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(holder?.findViewById(R.id.cropView))
            }
            return holder
        }

    }

}