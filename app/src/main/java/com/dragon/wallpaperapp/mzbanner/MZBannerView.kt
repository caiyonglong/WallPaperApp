package com.dragon.wallpaperapp.mzbanner

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.os.Handler
import android.support.annotation.AttrRes
import android.support.annotation.DrawableRes
import android.support.annotation.RequiresApi
import android.support.annotation.StyleRes
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Interpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Scroller
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mzbanner.holder.MZHolderCreator
import com.dragon.wallpaperapp.mzbanner.holder.MZViewHolder
import com.dragon.wallpaperapp.mzbanner.transformer.CoverModeTransformer
import com.dragon.wallpaperapp.mzbanner.transformer.ScaleYTransformer
import java.lang.reflect.Field
import java.util.*

/**
 * Created by zhouwei on 17/5/26.
 */

class MZBannerView<T> : RelativeLayout {
    private var mViewPager: CustomViewPager? = null
    private var mAdapter: MZPagerAdapter<Any>? = null
    private var mDatas: List<Any>? = null
    private var mIsAutoPlay = true// 是否自动播放
    private var mCurrentItem = 0//当前位置
    private val mHandler = Handler()
    private var mDelayedTime = 3000// Banner 切换时间间隔
    private var mViewPagerScroller: ViewPagerScroller? = null//控制ViewPager滑动速度的Scroller
    private var mIsOpenMZEffect = true// 开启魅族Banner效果
    private var mIsCanLoop = true// 是否轮播图片
    private var mIndicatorContainer: LinearLayout? = null//indicator容器
    private val mIndicators = ArrayList<ImageView>()
    //mIndicatorRes[0] 为为选中，mIndicatorRes[1]为选中
    private val mIndicatorRes = intArrayOf(R.drawable.indicator_normal, R.drawable.indicator_selected)
    private var mIndicatorPaddingLeft = 0// indicator 距离左边的距离
    private var mIndicatorPaddingRight = 0//indicator 距离右边的距离
    private var mIndicatorPaddingTop = 0//indicator 距离上边的距离
    private var mIndicatorPaddingBottom = 0//indicator 距离下边的距离
    private var mMZModePadding = 0//在仿魅族模式下，由于前后显示了上下一个页面的部分，因此需要计算这部分padding
    private var mIndicatorAlign = 1
    private var mOnPageChangeListener: ViewPager.OnPageChangeListener? = null
    private var mBannerPageClickListener: BannerPageClickListener? = null

    /**
     * 中间Page是否覆盖两边，默认覆盖
     */
    private var mIsMiddlePageCover = true


    private val mLoopRunnable = object : Runnable {
        override fun run() {
            if (mIsAutoPlay) {
                mCurrentItem = mViewPager!!.currentItem
                mCurrentItem++
                if (mCurrentItem == mAdapter!!.count - 1) {
                    mCurrentItem = 0
                    mViewPager!!.setCurrentItem(mCurrentItem, false)
                    mHandler.postDelayed(this, mDelayedTime.toLong())
                } else {
                    mViewPager!!.currentItem = mCurrentItem
                    mHandler.postDelayed(this, mDelayedTime.toLong())
                }
            } else {
                mHandler.postDelayed(this, mDelayedTime.toLong())
            }
        }
    }

    /**
     * 返回ViewPager
     *
     * @return [ViewPager]
     */
    val viewPager: ViewPager?
        get() = mViewPager

    /**
     * 获取Banner页面切换动画时间
     *
     * @return
     */
    /**
     * 设置ViewPager切换的速度
     *
     * @param duration 切换动画时间
     */
    var duration: Int
        get() = mViewPagerScroller!!.scrollDuration
        set(duration) {
            mViewPagerScroller!!.duration = duration
        }

    enum class IndicatorAlign {
        LEFT, //做对齐
        CENTER, //居中对齐
        RIGHT //右对齐
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        readAttrs(context, attrs)
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        readAttrs(context, attrs)
        init()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int, @StyleRes defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        readAttrs(context, attrs)
        init()
    }

    private fun readAttrs(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MZBannerView)
        mIsOpenMZEffect = typedArray.getBoolean(R.styleable.MZBannerView_open_mz_mode, true)
        mIsMiddlePageCover = typedArray.getBoolean(R.styleable.MZBannerView_middle_page_cover, true)
        mIsCanLoop = typedArray.getBoolean(R.styleable.MZBannerView_canLoop, true)
        mIndicatorAlign = typedArray.getInt(R.styleable.MZBannerView_indicatorAlign, 1)
        mIndicatorPaddingLeft = typedArray.getDimensionPixelSize(R.styleable.MZBannerView_indicatorPaddingLeft, 0)
        mIndicatorPaddingRight = typedArray.getDimensionPixelSize(R.styleable.MZBannerView_indicatorPaddingRight, 0)
        mIndicatorPaddingTop = typedArray.getDimensionPixelSize(R.styleable.MZBannerView_indicatorPaddingTop, 0)
        mIndicatorPaddingBottom = typedArray.getDimensionPixelSize(R.styleable.MZBannerView_indicatorPaddingBottom, 0)
    }


    private fun init() {
        var view: View? = null
        if (mIsOpenMZEffect) {
            view = LayoutInflater.from(context).inflate(R.layout.mz_banner_effect_layout, this, true)
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.mz_banner_normal_layout, this, true)
        }
        mIndicatorContainer = view!!.findViewById<View>(R.id.banner_indicator_container) as LinearLayout
        mViewPager = view.findViewById<View>(R.id.mzbanner_vp) as CustomViewPager
        mViewPager!!.offscreenPageLimit = 4

        mMZModePadding = dpToPx(30)
        // 初始化Scroller
        initViewPagerScroll()

        if (mIndicatorAlign == 0) {
            setIndicatorAlign(IndicatorAlign.LEFT)
        } else if (mIndicatorAlign == 1) {
            setIndicatorAlign(IndicatorAlign.CENTER)
        } else {
            setIndicatorAlign(IndicatorAlign.RIGHT)
        }


    }

    /**
     * 是否开启魅族模式
     */
    private fun setOpenMZEffect() {
        // 魅族模式
        if (mIsOpenMZEffect) {
            if (mIsMiddlePageCover) {
                // 中间页面覆盖两边，和魅族APP 的banner 效果一样。
                mViewPager!!.setPageTransformer(true, CoverModeTransformer(mViewPager))
            } else {
                // 中间页面不覆盖，页面并排，只是Y轴缩小
                mViewPager!!.setPageTransformer(false, ScaleYTransformer())
            }

        }
    }

    /**
     * 设置ViewPager的滑动速度
     */
    private fun initViewPagerScroll() {
        try {
            var mScroller: Field? = null
            mScroller = ViewPager::class.java.getDeclaredField("mScroller")
            mScroller!!.isAccessible = true
            mViewPagerScroller = ViewPagerScroller(
                    mViewPager!!.context)
            mScroller.set(mViewPager, mViewPagerScroller)

        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }


    /**
     * 初始化指示器Indicator
     */
    private fun initIndicator() {
        mIndicatorContainer!!.removeAllViews()
        mIndicators.clear()
        for (i in mDatas!!.indices) {
            val imageView = ImageView(context)
            if (mIndicatorAlign == IndicatorAlign.LEFT.ordinal) {
                if (i == 0) {
                    val paddingLeft = if (mIsOpenMZEffect) mIndicatorPaddingLeft + mMZModePadding else mIndicatorPaddingLeft
                    imageView.setPadding(paddingLeft + 6, 0, 6, 0)
                } else {
                    imageView.setPadding(6, 0, 6, 0)
                }

            } else if (mIndicatorAlign == IndicatorAlign.RIGHT.ordinal) {
                if (i == mDatas!!.size - 1) {
                    val paddingRight = if (mIsOpenMZEffect) mMZModePadding + mIndicatorPaddingRight else mIndicatorPaddingRight
                    imageView.setPadding(6, 0, 6 + paddingRight, 0)
                } else {
                    imageView.setPadding(6, 0, 6, 0)
                }

            } else {
                imageView.setPadding(6, 0, 6, 0)
            }

            if (i == mCurrentItem % mDatas!!.size) {
                imageView.setImageResource(mIndicatorRes[1])
            } else {
                imageView.setImageResource(mIndicatorRes[0])
            }

            mIndicators.add(imageView)
            mIndicatorContainer!!.addView(imageView)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (!mIsCanLoop) {
            return super.dispatchTouchEvent(ev)
        }
        when (ev.action) {
        // 按住Banner的时候，停止自动轮播
            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_OUTSIDE, MotionEvent.ACTION_DOWN -> {
                val paddingLeft = mViewPager!!.left
                val touchX = ev.rawX
                // 如果是魅族模式，去除两边的区域
                if (touchX >= paddingLeft && touchX < getScreenWidth(context) - paddingLeft) {
                    mIsAutoPlay = false
                }
            }
            MotionEvent.ACTION_UP -> mIsAutoPlay = true
        }
        return super.dispatchTouchEvent(ev)
    }

    /** */
    /**                             对外API                                                                */
    /** */
    /**
     * 开始轮播
     *
     * 应该确保在调用用了[{][.setPages] 之后调用这个方法开始轮播
     */
    fun start() {
        // 如果Adapter为null, 说明还没有设置数据，这个时候不应该轮播Banner
        if (mAdapter == null) {
            return
        }
        if (mIsCanLoop) {
            mIsAutoPlay = true
            mHandler.postDelayed(mLoopRunnable, mDelayedTime.toLong())
        }
    }

    /**
     * 停止轮播
     */
    fun pause() {
        mIsAutoPlay = false
        mHandler.removeCallbacks(mLoopRunnable)
    }

    /**
     * 设置BannerView 的切换时间间隔
     *
     * @param delayedTime
     */
    fun setDelayedTime(delayedTime: Int) {
        mDelayedTime = delayedTime
    }

    fun addPageChangeLisnter(onPageChangeListener: ViewPager.OnPageChangeListener) {
        mOnPageChangeListener = onPageChangeListener
    }

    /**
     * 添加Page点击事件
     *
     * @param bannerPageClickListener [BannerPageClickListener]
     */
    fun setBannerPageClickListener(bannerPageClickListener: BannerPageClickListener) {
        mBannerPageClickListener = bannerPageClickListener
    }

    /**
     * 是否显示Indicator
     *
     * @param visible true 显示Indicator，否则不显示
     */
    fun setIndicatorVisible(visible: Boolean) {
        if (visible) {
            mIndicatorContainer!!.visibility = View.VISIBLE
        } else {
            mIndicatorContainer!!.visibility = View.GONE
        }
    }

    /**
     * 设置indicator 图片资源
     *
     * @param unSelectRes 未选中状态资源图片
     * @param selectRes   选中状态资源图片
     */
    fun setIndicatorRes(@DrawableRes unSelectRes: Int, @DrawableRes selectRes: Int) {
        mIndicatorRes[0] = unSelectRes
        mIndicatorRes[1] = selectRes
    }

    /**
     * 设置数据，这是最重要的一个方法。
     *
     * 其他的配置应该在这个方法之前调用
     *
     * @param datas           Banner 展示的数据集合
     * @param mzHolderCreator ViewHolder生成器 [MZHolderCreator] And [MZViewHolder]
     */
     fun setPages(datas: List<Any>?, mzHolderCreator: MZHolderCreator<*>?) {
        if (datas == null || mzHolderCreator == null) {
            return
        }
        mDatas = datas
        //如果在播放，就先让播放停止
        pause()

        //增加一个逻辑：由于魅族模式会在一个页面展示前后页面的部分，因此，数据集合的长度至少为3,否则，自动为普通Banner模式
        //不管配置的:open_mz_mode 属性的值是否为true

        if (datas.size < 3) {
            mIsOpenMZEffect = false
            val layoutParams = mViewPager!!.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.setMargins(0, 0, 0, 0)
            mViewPager!!.layoutParams = layoutParams
            clipChildren = true
            mViewPager!!.clipChildren = true
        }
        setOpenMZEffect()
        // 2017.7.20 fix：将Indicator初始化放在Adapter的初始化之前，解决更新数据变化更新时crush.
        //初始化Indicator
        initIndicator()

        mAdapter = MZPagerAdapter(datas, mzHolderCreator, mIsCanLoop)
        mAdapter!!.setUpViewViewPager(mViewPager as ViewPager)
        mAdapter!!.setPageClickListener(mBannerPageClickListener)


        mViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                val realPosition = position % mIndicators.size
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener!!.onPageScrolled(realPosition, positionOffset, positionOffsetPixels)
                }
            }

            override fun onPageSelected(position: Int) {
                mCurrentItem = position


                // 切换indicator
                val realSelectPosition = mCurrentItem % mIndicators.size
                for (i in mDatas!!.indices) {
                    if (i == realSelectPosition) {
                        mIndicators[i].setImageResource(mIndicatorRes[1])
                    } else {
                        mIndicators[i].setImageResource(mIndicatorRes[0])
                    }
                }
                // 不能直接将mOnPageChangeListener 设置给ViewPager ,否则拿到的position 是原始的positon
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener!!.onPageSelected(realSelectPosition)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                when (state) {
                    ViewPager.SCROLL_STATE_DRAGGING -> mIsAutoPlay = false
                    ViewPager.SCROLL_STATE_SETTLING -> mIsAutoPlay = true
                }
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener!!.onPageScrollStateChanged(state)
                }
            }
        })


    }

    /**
     * 设置Indicator 的对齐方式
     *
     * @param indicatorAlign [IndicatorAlign.CENTER][IndicatorAlign.LEFT][IndicatorAlign.RIGHT]
     */
    fun setIndicatorAlign(indicatorAlign: IndicatorAlign) {
        mIndicatorAlign = indicatorAlign.ordinal
        val layoutParams = mIndicatorContainer!!.layoutParams as RelativeLayout.LayoutParams
        if (indicatorAlign == IndicatorAlign.LEFT) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        } else if (indicatorAlign == IndicatorAlign.RIGHT) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        } else {
            layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
        }

        // 2017.8.27 添加：增加设置Indicator 的上下边距。

        layoutParams.setMargins(0, mIndicatorPaddingTop, 0, mIndicatorPaddingBottom)
        mIndicatorContainer!!.layoutParams = layoutParams

    }

    /**
     * 设置是否使用ViewPager默认是的切换速度
     *
     * @param useDefaultDuration 切换动画时间
     */
    fun setUseDefaultDuration(useDefaultDuration: Boolean) {
        mViewPagerScroller!!.isUseDefaultDuration = useDefaultDuration
    }


    class MZPagerAdapter<T>(datas: List<Any>, private val mMZHolderCreator: MZHolderCreator<*>, private val canLoop: Boolean) : PagerAdapter() {
        private var mDatas: MutableList<Any>? = null
        private var mViewPager: ViewPager? = null
        private var mPageClickListener: BannerPageClickListener? = null
        private val mLooperCountFactor = 500

        private// 我们设置当前选中的位置为Integer.MAX_VALUE / 2,这样开始就能往左滑动
                // 但是要保证这个值与getRealPosition 的 余数为0，因为要从第一页开始显示
                // 直到找到从0开始的位置
        val startSelectItem: Int
            get() {
                var currentItem = realCount * mLooperCountFactor / 2
                if (currentItem % realCount == 0) {
                    return currentItem
                }
                while (currentItem % realCount != 0) {
                    currentItem++
                }
                return currentItem
            }

        /**
         * 获取真实的Count
         *
         * @return
         */
        private val realCount: Int
            get() = if (mDatas == null) 0 else mDatas!!.size

        init {
            if (mDatas == null) {
                mDatas = ArrayList()
            }
            //mDatas.add(data.get(data.size()-1));// 加入最后一个
            for (t in datas) {
                mDatas!!.add(t)
            }
        }// mDatas.add(data.get(0));//在最后加入最前面一个

        fun setPageClickListener(pageClickListener: BannerPageClickListener?) {
            mPageClickListener = pageClickListener
        }

        /**
         * 初始化Adapter和设置当前选中的Item
         *
         * @param viewPager
         */
        fun setUpViewViewPager(viewPager: ViewPager) {
            mViewPager = viewPager
            mViewPager!!.adapter = this
            mViewPager!!.adapter.notifyDataSetChanged()
            val currentItem = if (canLoop) startSelectItem else 0
            //设置当前选中的Item
            mViewPager!!.currentItem = currentItem
        }

        fun setDatas(datas: MutableList<Any>) {
            mDatas = datas
        }

        override fun getCount(): Int {
            // 2017.6.10 bug fix
            // 如果getCount 的返回值为Integer.MAX_VALUE 的话，那么在setCurrentItem的时候会ANR(除了在onCreate 调用之外)
            return if (canLoop) realCount * mLooperCountFactor else realCount//ViewPager返回int 最大值
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = getView(position, container)
            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun finishUpdate(container: ViewGroup) {
            // 轮播模式才执行
            if (canLoop) {
                var position = mViewPager!!.currentItem
                if (position == count - 1) {
                    position = 0
                    setCurrentItem(position)
                }
            }

        }

        private fun setCurrentItem(position: Int) {
            try {
                mViewPager!!.setCurrentItem(position, false)
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }

        }

        /**
         * @param position
         * @param container
         * @return
         */
        private fun getView(position: Int, container: ViewGroup): View {

            val realPosition = position % realCount
            var holder: MZViewHolder<*>? = null
            // create holder
            holder = mMZHolderCreator.createViewHolder()

            if (holder == null) {
                throw RuntimeException("can not return a null holder")
            }
            // create View
            val view = holder.createView(container.context)

            if (mDatas != null && mDatas!!.size > 0) {
                holder.onBind(container.context, realPosition, mDatas!![realPosition])
            }

            // 添加点击事件
            view.setOnClickListener { v ->
                if (mPageClickListener != null) {
                    mPageClickListener!!.onPageClick(v, realPosition)
                }
            }

            return view
        }


    }

    /**
     * ＊由于ViewPager 默认的切换速度有点快，因此用一个Scroller 来控制切换的速度
     *
     * 而实际上ViewPager 切换本来就是用的Scroller来做的，因此我们可以通过反射来
     *
     * 获取取到ViewPager 的 mScroller 属性，然后替换成我们自己的Scroller
     */
    class ViewPagerScroller : Scroller {
        var scrollDuration = 800
            private set// ViewPager默认的最大Duration 为600,我们默认稍微大一点。值越大越慢。
        var isUseDefaultDuration = false

        constructor(context: Context) : super(context) {}

        constructor(context: Context, interpolator: Interpolator) : super(context, interpolator) {}

        constructor(context: Context, interpolator: Interpolator, flywheel: Boolean) : super(context, interpolator, flywheel) {}

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
            super.startScroll(startX, startY, dx, dy, scrollDuration)
        }

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
            super.startScroll(startX, startY, dx, dy, if (isUseDefaultDuration) duration else scrollDuration)
        }

        fun setDuration(duration: Int) {
            scrollDuration = duration
        }
    }

    /**
     * Banner page 点击回调
     */
    interface BannerPageClickListener {
        fun onPageClick(view: View, position: Int)
    }

    companion object {
        private val TAG = "MZBannerView"

        fun getScreenWidth(context: Context): Int {
            val resources = context.resources
            val dm = resources.displayMetrics
            return dm.widthPixels
        }

        fun dpToPx(dp: Int): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), Resources.getSystem().displayMetrics).toInt()
        }
    }

}
