package com.dragon.wallpaperapp.ui.activity

import android.content.DialogInterface
import android.content.res.Configuration
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log.e
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
import kotlinx.android.synthetic.main.activity_wallpaper.*
import java.util.*

/**
 * Created by D22434 on 2017/12/6.
 */
class WallpaperDisplayActivity : AppCompatActivity(), WallpaperDisplayContract.View {

    var mPresenter: WallpaperDisplayPresenter = WallpaperDisplayPresenter()
    private var mWallpapers: List<Wallpaper> = ArrayList<Wallpaper>() as List<Wallpaper>
    private var mPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

        mPresenter.attachView(this)
        initData()
        initListener()
        //presenter load data
        mPresenter.loadData(mWallpapers, mPosition)

    }

    private fun initViewPager() {
        viewPager.offscreenPageLimit = 4
        viewPager.adapter = object : PagerAdapter() {

            override fun getCount(): Int {
                return mWallpapers.size
            }

            override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
                container!!.removeView(`object` as View)
            }

            override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
                return view == `object`
            }

            override fun instantiateItem(container: ViewGroup?, position: Int): Any {
                val view = getView(position, container)
                container!!.addView(view)
                return view
            }
        }

//        viewPager.setOnScrollChangeListener(o)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                mPosition = position
            }

        })
    }

    private fun getView(position: Int, container: ViewGroup?): View {
//        val realPosition = position % realCount
//        // create holder
        var holder = createViewHolder()
        // create View

        if (mWallpapers!!.isNotEmpty()) {
//            holder.onBind(container.context, realPosition, mDatas!![realPosition])
            GlideApp.with(this)
                    .load(mWallpapers.get(position).wp)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.findViewById(R.id.cropView))
        }
        return holder
    }

    private fun createViewHolder(): View {
        val view = LayoutInflater.from(this).inflate(R.layout.wp_image, null)
        return view
    }

    private fun initListener() {

        btn_set_wallpaper.setOnClickListener { _ ->
            AlertDialog.Builder(this)
                    .setItems(R.array.which_wallpaper_options, DialogInterface.OnClickListener { dialog, which ->
                        mPresenter.saveWallpaper(mWallpapers[mPosition].wp!!, which)
                    })
                    .create()
                    .show()
        }

    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        var orientation = newConfig!!.orientation
    }

    private fun initData() {
        mWallpapers = intent.getParcelableArrayListExtra("wallpapers")
        mPosition = intent.getIntExtra("position", -1)
        e("TAG", "mWallpaper = " + mWallpapers.size + "poi = " + mPosition)
        //没有数据直接返回
        if (mPosition == -1) {
            finish()
        }
        initViewPager()

        viewPager.currentItem = mPosition
    }

    override fun showLoading() {
        //init CropView
        viewPager.visibility = View.INVISIBLE
        //init Progress
        loading.visibility = View.VISIBLE

    }

    override fun hideLoading() {
        //init CropView
        viewPager.visibility = View.VISIBLE
        //init Progress
        loading.visibility = View.INVISIBLE
    }

    override fun preViewWallpaper(mBitmap: Bitmap) {
    }

}