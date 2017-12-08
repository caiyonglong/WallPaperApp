package com.dragon.wallpaperapp.ui.fragment;

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.contract.CategoryContract
import com.dragon.wallpaperapp.mvp.model.Category
import com.dragon.wallpaperapp.mvp.presenter.CategoryPresenter
import com.dragon.wallpaperapp.ui.activity.CategoryActivity
import com.dragon.wallpaperapp.ui.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_homepage.*


/**
 * Created by D22434 on 2017/11/28.
 */

class CategoryFragment : Fragment(), CategoryContract.View {


    var mPresenter: CategoryPresenter = CategoryPresenter()

    var mCurrentCounter = 0
    var TOTAL_COUNTER = 0
    var isErr = false

    private lateinit var mAdapter: CategoryAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_homepage, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)

        recyclerView.layoutManager = GridLayoutManager(activity, 2) as RecyclerView.LayoutManager?
        mAdapter = CategoryAdapter(null)
        recyclerView.adapter = mAdapter

        mPresenter.attachView(this)
        mPresenter.getCategory()

        mAdapter.disableLoadMoreIfNotFullPage(recyclerView)
        mAdapter.setEnableLoadMore(true)
        mAdapter.isUpFetchEnable = false
        mAdapter.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
            recyclerView.postDelayed(Runnable {
                if (mCurrentCounter >= TOTAL_COUNTER) {
                    //Data are all loaded.
                    mAdapter.loadMoreEnd()
                } else {
                    if (isErr) {
                        //Successfully get more data
                        mPresenter.getCategory()
                        mCurrentCounter = mAdapter.data.size
                        mAdapter.loadMoreComplete()
                    } else {
                        //Get more data failed
                        isErr = true
                        mAdapter.loadMoreFail()

                    }
                }
            }, 100)
        }, recyclerView)

        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { mAdapter, view, position ->
            val mCate: Category = mAdapter.data[position] as Category
            Log.e("TAG", mCate.name)
            var intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("name", mCate.name)
            intent.putExtra("id", mCate.id)
            startActivity(intent)
        }
    }

    override fun showCategory(categories: List<Category>?) {
        mAdapter.setNewData(categories)
        mAdapter.notifyDataSetChanged()
    }

    override fun showError(error: String) {
        Log.e("TAG", error)
    }

}
