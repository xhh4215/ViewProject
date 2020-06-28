package com.xh.kotlin.kotlin.viewmeasure

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import kotlin.math.max

class FlowLayout : ViewGroup {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)
    constructor(context: Context, attributes: AttributeSet, defStyle: Int) : super(
        context,
        attributes,
        defStyle
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measureWidthSize = MeasureSpec.getSize(widthMeasureSpec)
        val measureHeightSize = MeasureSpec.getSize(heightMeasureSpec)
        val measureWidthModel = MeasureSpec.getMode(widthMeasureSpec)
        val measureHeightModel = MeasureSpec.getMode(heightMeasureSpec)
        var lineHeight = 0
        var lineWidth = 0
        var totalHeight = 0
        var totalWidth = 0
        val childCount = childCount
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            //对子控件进行测量
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            //获取子的magrin的布局参数
            val marginLayoutParams = child.layoutParams as MarginLayoutParams
            // 添加了margin的高度
            var childHeight =
                child.measuredHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin
            // 添加了margin的宽度
            var childWidth =
                child.measuredWidth + marginLayoutParams.rightMargin + marginLayoutParams.leftMargin
            // 如果在添加一个子控件的宽度就大于父控件的宽度的时候应该执行换行操作
            if (childWidth + lineWidth > measureWidthSize) {
                //
                totalHeight += lineHeight
                totalWidth = max(childWidth, lineWidth)
                lineWidth = childWidth
                lineHeight = childHeight
                //添加子控件的操作
            } else {
                //累加子控件的宽度到lineWidth
                lineWidth += childWidth
                lineHeight = max(lineHeight, childHeight)
            }
            if (i == childCount - 1) {
                totalHeight += lineHeight
                totalWidth = max(totalWidth, lineWidth)
            }

        }
        when {
            measureWidthModel == MeasureSpec.EXACTLY && measureHeightModel == MeasureSpec.EXACTLY -> {
                setMeasuredDimension(measureWidthSize, measureHeightSize)

            }
            measureWidthModel == MeasureSpec.EXACTLY && measureHeightModel == MeasureSpec.AT_MOST -> {
                setMeasuredDimension(measureWidthSize, totalHeight)

            }
            measureWidthModel == MeasureSpec.AT_MOST && measureHeightModel == MeasureSpec.EXACTLY -> {
                setMeasuredDimension(totalWidth, measureHeightSize)

            }
            measureWidthModel == MeasureSpec.AT_MOST && measureHeightModel == MeasureSpec.AT_MOST -> {
                setMeasuredDimension(totalWidth, totalHeight)

            }
        }


    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val childCount = childCount
        var lineWidth = 0
        var lineHeight = 0
        var top = 0
        var left = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val marginLayoutParams = child.layoutParams as MarginLayoutParams
            // 添加了margin的高度
            var childHeight =
                child.measuredHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin
            // 添加了margin的宽度
            var childWidth =
                child.measuredWidth + marginLayoutParams.rightMargin + marginLayoutParams.leftMargin
            if (lineWidth + childWidth > measuredWidth) {
                top += lineHeight
                left = 0
                lineHeight = childHeight
                lineWidth = childWidth
            } else {
                lineHeight = max(lineHeight, childHeight)
                lineWidth += childWidth
            }
            var lc = left + marginLayoutParams.leftMargin
            var tc = top + marginLayoutParams.topMargin
            var rc = lc + child.measuredWidth
            var bc = tc + child.measuredHeight
            child.layout(lc, tc, rc, bc)
            left += child.width


        }
    }


    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    /***
     * 这个方法是用来设置  标识 FLAG_DISALLOW_INSERCERT 一旦设置成功ViewGroup将无法拦截除
     * ACTION_DOWN以外的其他事件
     */
    override fun requestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        super.requestDisallowInterceptTouchEvent(disallowIntercept)
    }
    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}