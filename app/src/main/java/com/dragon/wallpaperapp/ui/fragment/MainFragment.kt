package com.dragon.wallpaperapp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.ui.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*


/**
 * Created by D22434 on 2017/11/29.
 */

class MainFragment : Fragment() {

    var fragments: ArrayList<Fragment> = arrayListOf()

    var titles = arrayOf("推荐", "最新", "分类", "影视")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        initFragments()
        initTabLayout()
    }

    private fun initFragments() {
        fragments.clear()
        fragments.add(HomePageFragment.newInstance("new"))
        fragments.add(WallpaperFragment.newInstance("", "new", "new"))
        fragments.add(CategoryFragment())
        fragments.add(FilmFragment())
    }

    private fun initTabLayout() {
        viewPager.offscreenPageLimit = 4
        viewPager.adapter = ViewPagerAdapter(fragmentManager, titles, fragments)
        tabLayout.setViewPager(viewPager, titles)
    }

}
