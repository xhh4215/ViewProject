package com.xh.kotlin.kotlin.view

import android.content.Context
import android.util.AttributeSet
import android.view.View

/****
 * SpecMode的解析
 *
 * unspecified：父容器不对自容器作任何限制，要多大给多大，一般用于系统的内部测量
 * exacity：父容器检测出来View要使用的具体的大小，这个时候大小有spacsize确定， 对应于match_parent 和具体数值
 * at_most：父容器指定一个可用的大小，View的大小不能大于这个值，对用于 wrap_content
 *
 * 针对于DecorView：其MeasureSpec 由窗口的尺寸和他自身的LayoutParams决定
 * 对于普通的View：其MeasureSpec 由父容器的MeasureSpec和自身的Layoutparam决定
 *
 * View的工作流程
 *
 *  measure  layout  draw 这三个流程 即 测量 ，布局，绘制
 *
 *  measure:是确定View的宽和高  layout:确定View的最终宽高和四个顶点的位置 draw:则是将View绘制到屏幕上
 *
 *  View的MeasureSpec是有父容器的SpecMode和子View的LayoutParam决定的

 *  View的measure过程：由内部的measure方法完成
 *  具体的绘制是通过OnMeasure()方法来实现的
 *          measure()
 *             |
 *         onMeasure() - setMeasuredimension() 设置view的宽高测量值
 *                             ｜
 *                        getDefaultSize()   1 返回measureSpec中的specsize
 *                                                    |
 *                                                    这里处理的是 AT_MOST 和EXACITY两种情况的默认值
 *                                                    即 spacsize
 *                                           2 返回的是第一个参数的值
 *                                                     |
 *                                                     这里处理的就是 UNSPECIFIED的情况下的默认值
 *                                                getSuggestedMinimumWidth()
 *                                                getSuggestedMinimumHeight()
 *                                                如果View没有设置背景那么返回的数值就是 minWidth这个数值指定的数值，默认是0
 *                                                如果View设置了背景则返回mingWidth和背景最小宽大两者中的最大值，高度的获取是相同的道理
 *  备注：直接继承View的自定义控件需要重写onMeasure方法设置wrap_cpntent的时候自身的大小，否则在布局中 wrap_content会和match_parent相同
 *
 */
class DrawView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measureHeightSize = MeasureSpec.getSize(heightMeasureSpec)
        val measureWidthSize = MeasureSpec.getSize(widthMeasureSpec)
        val measureWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        val measureHeightMode = MeasureSpec.getMode(heightMeasureSpec)
        when {
            measureHeightMode == MeasureSpec.AT_MOST && measureWidthMode == MeasureSpec.AT_MOST -> {
                setMeasuredDimension(200, 200)
            }
            measureWidthMode == MeasureSpec.AT_MOST -> {
                setMeasuredDimension(200, measureHeightSize)
            }
            measureHeightMode == MeasureSpec.AT_MOST -> {
                setMeasuredDimension(measureWidthSize, 200)
            }


        }
    }
}