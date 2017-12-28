package com.dragon.wallpaperapp.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
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
import com.mingle.widget.LoadingView
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
    private var order = ""
    private var mNextPage = 1
    private var mCurrentCounter = 0
    private var isErr = true


    companion object {

        fun newInstance(order: String): HomePageFragment {
            val args = Bundle()
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
        order = arguments.getString("order")
        mPresenter.attachView(this)
        initView()
        initAdapter()
        loadData()
    }

    private fun initView() {
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = GridLayoutManager(activity, 3)
    }

    private fun loadData() {
        Log.e("loadData", "limit=${limit}skip=$skip order=$order")
        mPresenter.getWallpaper(limit, skip, order)
    }

    private fun initAdapter() {
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)
        mAdapter.bindToRecyclerView(recyclerView)
        mAdapter.setEmptyView(R.layout.item_empty)
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
            if (mCurrentCounter < limit) {
                mAdapter.loadMoreEnd(true)//true is gone,false is visible
            } else {
                if (isErr) {
                    skip = (mNextPage - 1) * limit
                    loadData()
                    mAdapter.loadMoreComplete()
                } else {
                    isErr = true
                    mAdapter.loadMoreFail()

                }
            }
        }, 1000)

    }

    override fun showWallpaper(wallpapers: List<Wallpaper>?) {
        Log.e("showWallpaper", "----- $skip ---- $mNextPage")
        mCurrentCounter = wallpapers?.size ?: 0
        if (mNextPage == 1) {
            mAdapter.setNewData(wallpapers)
            mAdapter.disableLoadMoreIfNotFullPage(recyclerView)
            mNextPage++
        } else {
            if (mCurrentCounter > 0) {
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
        mAdapter.emptyView.findViewById<LoadingView>(R.id.loading_view).setLoadingText(context.getText(R.string.load_error))
        mAdapter.loadMoreFail()
    }

    inner class BannerViewHolder : MZViewHolder<Banner> {
        private var mView: View? = null
        override fun onBind(p0: Context?, p1: Int, banner: Banner?) {
            GlideApp.with(context)
                    .load(banner?.lcover)
                    .placeholder(R.drawable.ic_default_preview)
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
        mAdapter.emptyView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        mAdapter.emptyView.visibility = View.INVISIBLE
    }
}
