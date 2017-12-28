package com.dragon.wallpaperapp.ui.fragment;

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.contract.CategoryContract
import com.dragon.wallpaperapp.mvp.model.bean.Category
import com.dragon.wallpaperapp.mvp.presenter.CategoryPresenter
import com.dragon.wallpaperapp.ui.activity.CategoryActivity
import com.dragon.wallpaperapp.ui.adapter.CategoryAdapter
import com.mingle.widget.LoadingView
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_recyclerview.*


/**
 * Created by D22434 on 2017/11/28.
 */

class CategoryFragment : Fragment(), CategoryContract.View {

    private var mPresenter: CategoryPresenter = CategoryPresenter()
    private lateinit var mAdapter: CategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        initAdapter()
        mPresenter.attachView(this)
        mPresenter.getCategory()
    }

    private fun initAdapter() {
        mAdapter = CategoryAdapter(null)
        mAdapter.bindToRecyclerView(recyclerView)
        mAdapter.setEmptyView(R.layout.item_empty)
        mAdapter.disableLoadMoreIfNotFullPage(recyclerView)
        mAdapter.setEnableLoadMore(true)
        mAdapter.isUpFetchEnable = false
        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { mAdapter, _, position ->
            val mCate: Category = mAdapter.data[position] as Category
            Logger.e("TAG", mCate.name)
            var intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("name", mCate.name)
            intent.putExtra("cover", mCate.cover)
            intent.putExtra("id", mCate.id)
            intent.putExtra("type", "category")
            startActivity(intent)
        }
    }

    override fun showCategory(categories: List<Category>?) {
        if (categories != null) {
            mAdapter.addData(categories)
        }
    }

    override fun showError(error: String) {
        mAdapter.emptyView.findViewById<LoadingView>(R.id.loading_view).setLoadingText(context.getText(R.string.load_error))
    }


    override fun showLoading() {
        mAdapter.emptyView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        mAdapter.emptyView.visibility = View.INVISIBLE
    }

}
