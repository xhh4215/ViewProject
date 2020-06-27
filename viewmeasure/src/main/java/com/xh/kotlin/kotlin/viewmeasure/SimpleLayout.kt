package com.xh.kotlin.kotlin.viewmeasure

import android.content.Context
import android.icu.util.Measure
import android.util.AttributeSet
import android.view.ViewGroup
import kotlin.math.max

class SimpleLayout : ViewGroup {


    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)
    constructor(context: Context, attributes: AttributeSet, defStyle: Int) : super(
        context,
        attributes,
        defStyle
    )


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //宽度和高度的测量模式
        val widthModel = MeasureSpec.getMode(widthMeasureSpec)
        val heightModel = MeasureSpec.getMode(heightMeasureSpec)
        //高度和宽度的大小
        val widthsize = MeasureSpec.getSize(widthMeasureSpec)
        val heightsize = MeasureSpec.getSize(heightMeasureSpec)
        var totalHeight = 0
        var totalWidth = 0
        //获取子控件的个数
        val childCount = childCount
        //遍历子空间
        for (i in 0 until childCount) {
            var child = getChildAt(i)
            //测量子控件
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val marginLayoutParams = child.layoutParams as MarginLayoutParams
            //获取测量的子控件的宽度和高度
            val childwidth =
                child.measuredWidth + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin
            val childheight =
                child.measuredHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin
            totalHeight += childheight
            totalWidth = max(childwidth, totalWidth)
        }
        when {
            widthModel == MeasureSpec.EXACTLY && heightModel == MeasureSpec.EXACTLY -> {
                setMeasuredDimension(widthsize, heightsize)

            }
            widthModel == MeasureSpec.EXACTLY && heightModel == MeasureSpec.AT_MOST -> {
                setMeasuredDimension(widthsize, totalHeight)

            }
            widthModel == MeasureSpec.AT_MOST && heightModel == MeasureSpec.EXACTLY -> {
                setMeasuredDimension(totalWidth, heightsize)

            }
            widthModel == MeasureSpec.AT_MOST && heightModel == MeasureSpec.AT_MOST -> {
                setMeasuredDimension(totalWidth, totalHeight)

            }
        }

    }

    /***
     * getMeasureXXXXX() 是获取测量大小是在 onMeasure()之后获取的
     * getXXXX() 是在onLayout()之后获取的
     * getMeasureWidth()方法在measure()过程结束后就可以获取到了，而getWidth()方法要在layout()过程结束后才能获取到。再重申一遍！！！！！
     */
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var top = 0
        var measureHeight = 0
        var measureWidth = 0
        val childCount = childCount
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val marginLayoutParams = child.layoutParams as MarginLayoutParams
            measureHeight =
                child.measuredHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin
            measureWidth =
                child.measuredWidth + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin
            child.layout(0, top, measureWidth, top + measureHeight)
            top += measureHeight

        }
    }

    /***
     * 通过该方法为子控件生成对应的布局属性
     * LayoutParams：默认获取的是 LayoutParams对象只解析了布局参数中的  layout_width  和layout_height 两个布局参数
     * MarginLayoutParams：该类是 LayoutParams的子类 添加了对 margin相关的布局参数的解析
     */
    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
          //默认实现的是解析 layout_width  和layout_height 两个布局参数
//        super.generateLayoutParams(attrs)
        return MarginLayoutParams(context,attrs)
    }
    /***
     *如果要使用默认的构造方法，就生成layout_width="wrap_content"、layout_height="wrap_content"对应的参数
     */
    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
    }

}