package com.dragon.wallpaperapp.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.api.GlideApp
import com.dragon.wallpaperapp.mvp.contract.HomePageContract
import com.dragon.wallpaperapp.mvp.model.Banner
import com.dragon.wallpaperapp.mvp.model.bean.Wallpaper
import com.dragon.wallpaperapp.mvp.presenter.HomePagePresenter
import com.dragon.wallpaperapp.mzbanner.MZBannerView
import com.dragon.wallpaperapp.mzbanner.holder.MZHolderCreator
import com.dragon.wallpaperapp.mzbanner.holder.MZViewHolder
import com.dragon.wallpaperapp.ui.activity.CategoryActivity
import com.dragon.wallpaperapp.ui.activity.WallpaperDisplayActivity
import com.dragon.wallpaperapp.ui.adapter.HomeAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.banner_view.view.*
import kotlinx.android.synthetic.main.fragment_homepage.*
import java.util.*


/**
 * Created by D22434 on 2017/11/29.
 */

class HomePageFragment : Fragment(), HomePageContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    private var mPresenter: HomePagePresenter = HomePagePresenter()
    private var mAdapter: HomeAdapter = HomeAdapter(null)
    private var limit = 30
    private var skip = 0
    private var mNextPage = 1
    private var mCurrentCounter = 0
    private var isErr = true

    companion object {
        private var TOTAL_COUNTER = 100

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
        initView()
        initAdapter()
        mPresenter.getWallpaper(limit, skip, arguments.getString("order"))
    }

    private fun initView() {
        mPresenter.attachView(this)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = GridLayoutManager(activity, 3) as RecyclerView.LayoutManager?
    }

    private fun initAdapter() {
        mAdapter = HomeAdapter(null)
        mAdapter.openLoadAnimation()
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)
        recyclerView.adapter = mAdapter
        mCurrentCounter = mAdapter.data.size
        mAdapter.setOnLoadMoreListener(this, recyclerView)
        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            val mWallpapers: List<Wallpaper> = mAdapter.data
            val intent = Intent(activity, WallpaperDisplayActivity::class.java)
            intent.putExtra("position", position)
            intent.putParcelableArrayListExtra("wallpapers", mWallpapers as ArrayList<out Parcelable>?)
            startActivity(intent)
        }
    }

    override fun onLoadMoreRequested() {
        recyclerView.postDelayed({
            Log.e("loading ", "${mAdapter.isLoading}-----")
            if (mAdapter.data.size < limit) {
                mAdapter.loadMoreEnd(true)
            } else {
                if (mCurrentCounter >= TOTAL_COUNTER) {
                    //                    pullToRefreshAdapter.loadMoreEnd();//default visible
                    mAdapter.loadMoreEnd(true)//true is gone,false is visible
                } else {
                    if (isErr) {
                        skip = (mNextPage - 1) * limit
                        Log.e("showWallpaper", "----- $skip ---- $mNextPage")
                        mPresenter.getWallpaper(limit, skip, arguments.getString("order"))
                        mCurrentCounter = mAdapter.data.size
                        mAdapter.loadMoreComplete()
                    } else {
                        isErr = true
                        mAdapter.loadMoreFail()

                    }
                }
            }
        }, 1000)

    }


    override fun showWallpaper(wallpapers: List<Wallpaper>?) {
        Log.e("showWallpaper", "----- $skip ---- $mNextPage")
        val size = wallpapers?.size ?: 0
        if (mNextPage == 1) {
            mAdapter.setNewData(wallpapers)
            mAdapter.disableLoadMoreIfNotFullPage(recyclerView)
            mNextPage++
        } else {
            if (size > 0) {
                mAdapter.addData(wallpapers!!)
                mNextPage++
            }
        }
    }

    override fun showBanners(banners: List<Banner>) {
        Logger.e(banners.toString())
        val bannerView: View = layoutInflater.inflate(R.layout.banner_view, recyclerView.parent as ViewGroup, false)
        bannerView.banner.setBannerPageClickListener(object : MZBannerView.BannerPageClickListener {
            override fun onPageClick(view: View, position: Int) {
                Logger.e("onPageCli sck $position")
                val intent = Intent(context, CategoryActivity::class.java)
                intent.putExtra("position", position)
                intent.putExtra("id", banners[position].id)
                intent.putExtra("cover", banners[position].lcover)
                intent.putExtra("name", banners[position].name)
                intent.putExtra("type", "album")
                context.startActivity(intent)
            }
        })
        bannerView.banner.setIndicatorVisible(false)
        bannerView.banner.setPages(banners,
                MZHolderCreator {
                    BannerViewHolder()
                }
        )
        mAdapter.addHeaderView(bannerView)
        recyclerView.adapter = mAdapter
    }


    override fun showError(error: String) {
        mAdapter.loadMoreFail()
    }

    inner class BannerViewHolder : MZViewHolder<Banner> {
        var mView: View? = null
        override fun onBind(p0: Context?, p1: Int, banner: Banner?) {
            GlideApp.with(context)
                    .load(banner?.lcover)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(mView?.findViewById(R.id.imageView))
        }

        @SuppressLint("InflateParams")
        override fun createView(p0: Context?): View? {
            mView = LayoutInflater.from(p0).inflate(R.layout.item_banner, null)
            return mView
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {
    }
}
