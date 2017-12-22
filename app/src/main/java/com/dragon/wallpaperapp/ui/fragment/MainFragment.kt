package com.dragon.wallpaperapp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.presenter.HomePagePresenter
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * Created by D22434 on 2017/11/29.
 */

class MainFragment : Fragment() {

    var mPresenter: HomePagePresenter = HomePagePresenter()

    var fragments: List<Fragment>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)

        initFragments()
        initTabLayout()

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

    }

    private fun initFragments() {
//        var titles:Array<String>
    }

    private fun initTabLayout() {
//        tabLayout.setViewPager(viewPager, fragmentManager, )

//        /** no need to set titles in adapter */
//        public void setViewPager(ViewPager vp, String[] titles)
//
//        /** no need to initialize even adapter */
//        public void setViewPager(ViewPager vp, String[] titles, FragmentActivity fa, ArrayList<Fragment> fragments)

    }

}
