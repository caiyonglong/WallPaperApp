package com.dragon.wallpaperapp.ui.fragment;

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.contract.CategoryContract
import com.dragon.wallpaperapp.mvp.model.Category
import com.dragon.wallpaperapp.mvp.presenter.CategoryPresenter
import com.dragon.wallpaperapp.ui.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_homepage.*


/**
 * Created by D22434 on 2017/11/28.
 */

class CategoryFragment : Fragment(), CategoryContract.View {


    var mPresenter: CategoryPresenter = CategoryPresenter()


    private lateinit var mAdapter: CategoryAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_homepage, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)


        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        mAdapter = CategoryAdapter(null)
        recyclerView.adapter = mAdapter

        mPresenter.attachView(this)
        mPresenter.getCategory()
    }


    override fun showCategory(categories: List<Category>?) {
        mAdapter.setNewData(categories)
        mAdapter.notifyDataSetChanged()
    }

    override fun showError(error: String) {
        Log.e("TAG", error)
    }

}
