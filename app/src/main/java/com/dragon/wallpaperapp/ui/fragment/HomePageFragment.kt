package com.dragon.wallpaperapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.api.GlideApp
import com.dragon.wallpaperapp.mvp.contract.HomePageContract
import com.dragon.wallpaperapp.mvp.model.Banner
import com.dragon.wallpaperapp.mvp.model.Wallpaper
import com.dragon.wallpaperapp.mvp.presenter.HomePagePresenter
import com.dragon.wallpaperapp.mzbanner.holder.MZHolderCreator
import com.dragon.wallpaperapp.mzbanner.holder.MZViewHolder
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_homepage.*


/**
 * Created by D22434 on 2017/11/29.
 */

class HomePageFragment : Fragment(), HomePageContract.View {


    var mPresenter: HomePagePresenter = HomePagePresenter()

    var mCurrentCounter = 30
    var TOTAL_COUNTER = 60
    var isErr = true

    companion object {
        fun newInstance(order: String): HomePageFragment {
            val args: Bundle = Bundle()
            args.putString("order", order)
            val fragment = HomePageFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_homepage, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)

        tabLayout.addTab(tabLayout.newTab().setText("推荐"))
        tabLayout.addTab(tabLayout.newTab().setText("最新"))

        viewPager.adapter = object : FragmentPagerAdapter(fragmentManager) {

            override fun getCount(): Int {
                return mTitles.size
            }

            private val mTitles = arrayOf("最新", "最热")

            override fun getItem(position: Int): Fragment {
                return if (position == 0) {
                    WallpaperFragment.newInstance("", "new", "new")
                } else {
                    WallpaperFragment.newInstance("", "new", "new")
                }
            }

            override fun getPageTitle(position: Int): CharSequence {
                return mTitles[position]
            }

        }

        tabLayout.setupWithViewPager(viewPager)

        mPresenter.attachView(this)
        mPresenter.getWallpaper(30, 0, arguments.getString("order"))


    }

    override fun showWallpaper(wallpapers: List<Wallpaper>?) {

    }

    override fun showBanners(banners: List<Banner>) {
        Logger.e(banners.toString())
        banner.setPages(banners,
                MZHolderCreator {
                    BannerViewHolder()
                }
        )
        banner.setIndicatorVisible(false)
    }


    override fun showError(error: String) {
        Log.e("TAG", error)
    }

    inner class BannerViewHolder : MZViewHolder<Banner> {
        var mView: View? = null
        override fun onBind(p0: Context?, p1: Int, banner: Banner?) {
//            imageView.setImageBitmap(p2)

            GlideApp.with(context)
                    .load(banner?.lcover)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(mView?.findViewById(R.id.imageView))
        }

        override fun createView(p0: Context?): View? {
            mView = LayoutInflater.from(p0).inflate(R.layout.item_banner, null)
            return mView as View?
        }
    }
}
