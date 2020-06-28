package com.xh.kotlin.kotlin.viewmeasure

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import com.xh.kotlin.kotlin.interfaces.OnItemClickListener

open class WaterfallLayout : ViewGroup {

    //指定当前的列数
    private var column = 3

    //水平间距
    private var hSpace = 20

    //垂直间距
    private var vSpace = 20

    //当前图片的宽度
    private var childWidh = 0

    //保存当前每一列的高度
    private var top = IntArray(column)

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : this(context, attributeSet,0)
    constructor(context: Context, attributeSet: AttributeSet,defStyle:Int) : super(context, attributeSet,defStyle)

    /***
     * 进行的是自定义View的测量操作
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //获取宽度的测量模式
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        //获取宽度的具体数值
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        //对所有的子控件进行测量
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        //获取单个item的宽度
        childWidh = (widthSize - (column - 1) * hSpace) / column
        //得到的总宽度
        var wrapWidth: Int
        //获取子item的个数
        val childCount = childCount
        //子item的个数小于3时
        wrapWidth = if (childCount < column) {
            //计算当前情况的一个子View的宽度
            childCount * childWidh + (childCount - 1) * hSpace
        } else {
            //大于3之后的宽度就是父控件的执行的宽度
            widthSize
        }
        //清除数组
        clearTop()
        for (i in 0 until childCount) {
            var child = getChildAt(i)
            Log.d("tag","测量的ziview的高度${child.measuredWidth}")
            //获取子View的高度
            var childHeight = (child.measuredHeight * childWidh )/ child.measuredWidth
            //获取高度最小的那列
            var minColunm = getMinHeightColum()
            top[minColunm] += childHeight + vSpace

        }
        var wrapHeight: Int = getMaxHeight()
        setMeasuredDimension(
            if (widthMode == MeasureSpec.AT_MOST) wrapWidth else widthSize,
            wrapHeight
        )
    }

    /***
     * 自定义View的位置摆放的操作
     */
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childCount = childCount
        clearTop()
        for (i in 0 until childCount) {
            var child = getChildAt(i)
            val childHeight = child.measuredHeight * childWidh / child.measuredWidth
            val minColum = getMinHeightColum()
            val tleft = minColum * (childWidh + hSpace)
            val ttop = top[minColum]
            val tright = tleft + childWidh
            val tbottom = ttop + childHeight
            top[minColum] += vSpace + childHeight
            child.layout(tleft, ttop, tright, tbottom)

        }
    }

    private fun getMaxHeight(): Int {
        var maxHeight = 0
        for (i in 0 until column) {
            if (top[i] > maxHeight) {
                maxHeight = top[i]
            }
        }
        return maxHeight
    }

    private fun getMinHeightColum(): Int {
        var minColumn = 0
        for (i in 0 until column) {
            if (top[i] < top[minColumn]) {
                minColumn = i
            }
        }
        return minColumn
    }

    private fun clearTop() {
        for (i in 0 until column) {
            top[i] = 0
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {

        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view.setOnClickListener {
                listener.onItemClick(view, i)
            }
        }

    }


}
