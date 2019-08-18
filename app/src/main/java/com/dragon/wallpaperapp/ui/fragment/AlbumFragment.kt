package com.dragon.wallpaperapp.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.api.GlideApp
import com.dragon.wallpaperapp.mvp.contract.AlbumContract
import com.dragon.wallpaperapp.mvp.model.Banner
import com.dragon.wallpaperapp.mvp.model.bean.Album
import com.dragon.wallpaperapp.mvp.presenter.AlbumPresenter
import com.dragon.wallpaperapp.mzbanner.MZBannerView
import com.dragon.wallpaperapp.mzbanner.holder.MZHolderCreator
import com.dragon.wallpaperapp.mzbanner.holder.MZViewHolder
import com.dragon.wallpaperapp.ui.activity.CategoryActivity
import com.dragon.wallpaperapp.ui.adapter.AlbumAdapter
import com.mingle.widget.LoadingView
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.banner_view.view.*
import kotlinx.android.synthetic.main.fragment_recyclerview.*


/**
 * Created by D22434 on 2017/11/29.
 */

class AlbumFragment : androidx.fragment.app.Fragment(), AlbumContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    var mPresenter: AlbumPresenter = AlbumPresenter()
    lateinit var mAdapter: AlbumAdapter
    private var limit = 10
    private var skip = 0
    private var mNextPage = 1
    private var mCurrentCounter = 0
    private var isErr = true

    private var order = ""


    companion object {
        fun newInstance(order: String): AlbumFragment {
            val args = Bundle()
            args.putString("order", order)
            val fragment = AlbumFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_homepage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        order = arguments?.get("order").toString()
        initAdapter()
        mPresenter.attachView(this)
        loadData()
    }

    private fun loadData() {
        mPresenter.getAlbums(limit, skip, order)
    }

    private fun initAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter = AlbumAdapter(null)
        mAdapter.bindToRecyclerView(recyclerView)
        mAdapter.setEmptyView(R.layout.item_empty)
        mAdapter.setOnLoadMoreListener(this, recyclerView)
        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            val mAlbums: List<Album> = mAdapter.data
            val intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("position", position)
            intent.putExtra("id", mAlbums[position].id)
            intent.putExtra("desc", mAlbums[position].desc)
            intent.putExtra("cover", mAlbums[position].lcover)
            intent.putExtra("name", mAlbums[position].name)
            intent.putExtra("type", "album")
            startActivity(intent)
        }
    }

    override fun onLoadMoreRequested() {
        recyclerView.postDelayed({
            Log.e("loading ", "${mAdapter.isLoading}-----")
            if (mCurrentCounter < limit) {
                mAdapter.loadMoreEnd(true)
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

    override fun showAlbum(albums: List<Album>?) {
        Log.e("showWallpaper", "----- $skip ---- $mNextPage")
        mCurrentCounter = albums?.size ?: 0
        if (mNextPage == 1) {
            mAdapter.setNewData(albums)
            mAdapter.disableLoadMoreIfNotFullPage(recyclerView)
            mNextPage++
        } else {
            if (mCurrentCounter > 0) {
                mAdapter.addData(albums!!)
                mNextPage++
            }
        }
    }

    override fun showBanners(banners: List<Banner>) {
        Logger.e(banners.toString())
        val bannerView: View = layoutInflater.inflate(R.layout.banner_view, recyclerView.parent as ViewGroup, false)
        bannerView.banner.setBannerPageClickListener(object : MZBannerView.BannerPageClickListener {
            override fun onPageClick(view: View, position: Int) {

                Logger.e("onPageClick $position")
                val intent = Intent(context, CategoryActivity::class.java)
                intent.putExtra("position", position)
                intent.putExtra("id", banners[position].id)
                intent.putExtra("desc", banners[position].desc)
                intent.putExtra("cover", banners[position].thumb)
                intent.putExtra("name", banners[position].name)
                intent.putExtra("type", "album")
                context?.startActivity(intent)
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

    inner class BannerViewHolder : MZViewHolder<Banner> {
        var mView: View? = null
        override fun onBind(p0: Context?, p1: Int, banner: Banner?) {
            context?.let {
                mView?.findViewById<ImageView>(R.id.imageView)?.let { view ->
                    GlideApp.with(it)
                            .load(banner?.thumb)
                            .placeholder(R.drawable.ic_default_preview)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(view)
                }
            }
        }

        override fun createView(p0: Context?): View? {
            mView = LayoutInflater.from(p0).inflate(R.layout.item_banner, null)
            return mView
        }
    }

    override fun showError(error: String) {
        mAdapter.emptyView.findViewById<LoadingView>(R.id.loading_view).setLoadingText(context?.getText(R.string.load_error))
        Log.e("TAG", error)

    }

    override fun showLoading() {
        mAdapter.emptyView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        mAdapter.emptyView.visibility = View.INVISIBLE
    }
}
