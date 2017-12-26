package com.dragon.wallpaperapp.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
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
import com.dragon.wallpaperapp.mvp.contract.AlbumContract
import com.dragon.wallpaperapp.mvp.model.Banner
import com.dragon.wallpaperapp.mvp.model.bean.Album
import com.dragon.wallpaperapp.mvp.presenter.AlbumPresenter
import com.dragon.wallpaperapp.mzbanner.holder.MZHolderCreator
import com.dragon.wallpaperapp.mzbanner.holder.MZViewHolder
import com.dragon.wallpaperapp.ui.activity.CategoryActivity
import com.dragon.wallpaperapp.ui.adapter.AlbumAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.banner_view.view.*
import kotlinx.android.synthetic.main.fragment_homepage.*


/**
 * Created by D22434 on 2017/11/29.
 */

class AlbumFragment : Fragment(), AlbumContract.View {


    var mPresenter: AlbumPresenter = AlbumPresenter()
    lateinit var mAdapter: AlbumAdapter
    var mCurrentCounter = 30
    var TOTAL_COUNTER = 0
    var isErr = true

    companion object {
        fun newInstance(order: String): AlbumFragment {
            val args: Bundle = Bundle()
            args.putString("order", order)
            val fragment = AlbumFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_homepage, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        init()
        mPresenter.attachView(this)
        mPresenter.getAlbums(30, 0, arguments.getString("order"))
    }

    private fun init() {
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
        mAdapter = AlbumAdapter(null)
        recyclerView.adapter = mAdapter

//        mAdapter.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
//            recyclerView.postDelayed(Runnable {
//                if (mCurrentCounter >= TOTAL_COUNTER) {
//                    //Data are all loaded.
//                    mAdapter.loadMoreEnd()
//                } else {
//                    if (isErr) {
//                        //Successfully get more data
//                        mPresenter.getWallpaper(30, mCurrentCounter, "hot")
//                        mCurrentCounter = mAdapter.data.size
//                        mAdapter.loadMoreComplete()
//                    } else {
//                        //Get more data failed
//                        isErr = true
//                        mAdapter.loadMoreFail()
//                    }
//                }
//            }, 100)
//        }, recyclerView)

        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            val mAlbums: List<Album> = mAdapter.data
            val intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("position", position)
            intent.putExtra("id", mAlbums[position].id)
            intent.putExtra("cover", mAlbums[position].cover)
            intent.putExtra("name", mAlbums[position].name)
            intent.putExtra("type", "album")
            startActivity(intent)
        }
    }

    override fun showAlbum(albums: List<Album>?) {
        mAdapter.setNewData(albums)
        TOTAL_COUNTER = mAdapter.data.size + 30
        mAdapter.notifyDataSetChanged()
    }

    override fun showBanners(banners: List<Banner>) {
        Logger.e(banners.toString())

        var bannerView: View = layoutInflater.inflate(R.layout.banner_view, recyclerView.parent as ViewGroup, false)
        bannerView.banner.setPages(banners,
                MZHolderCreator {
                    BannerViewHolder()
                }
        )
        bannerView.banner.setIndicatorVisible(false)
        mAdapter.addHeaderView(bannerView)
        mAdapter.notifyDataSetChanged()
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

    override fun showLoading() {

    }

    override fun hideLoading() {
    }
}
