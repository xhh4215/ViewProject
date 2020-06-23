package com.xh.kotlin.kotlin.viewpart.viewbase

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.xh.kotlin.kotlin.viewpart.R

class PaintView : View {
    //初始化一个画笔对象
    private var paint = Paint()


    private var color: Int? = Color.RED

    private var width: Float? = 5f

    constructor(context: Context) : super(context) {
        setPaint(paint)
    }

    private fun setPaint(paint: Paint) {
        //设置画笔的样式
        paint.style = Paint.Style.STROKE
        //设置画笔是否抗锯齿
        paint.isAntiAlias = true
        //设置画笔的宽度
        paint.strokeWidth = 1f
        //设置画笔的颜色


    }


    @SuppressLint("Range")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //设置画板的颜色
        //?.  相当于 在调用drawRGB对canvas进行判断 不为空的时候点用方法为空的时候返回null
        canvas?.drawRGB(255, 255, 255)
        //画一个圆
        canvas?.drawCircle(100f, 100f, 50f, paint)
        //?:相当于为color为null的时候添加了一个默认值
        paint.color = color ?: Color.GREEN
        //let就是讲一个变量转化到他对应的lambda表达式内部
        width?.let {
            paint.strokeWidth = it
        }
        val string = "小黑的自定义View之路"
        if (string.isGood()) {
            canvas?.drawText(string, 300f, 300f, paint)

        }
        //创建一个整型数组
        val number = FloatArray(8)
        number[0] = 10f
        number[1] = 10f
        number[2] = 100f
        number[3] = 100f
        number[4] = 200f
        number[5] = 200f
        number[6] = 400f
        number[7] = 400f
        //绘制文本
//        canvas?.drawLines(number,paint)


//        paint.color = Color.GREEN
        //绘制一个点
//        canvas?.drawPoint(500f, 500f, paint)
        //绘制多个点
//        canvas?.drawPoints(number, paint)
//        paint.color = Color.RED
        //矩形辅助类
//        val rectf = RectF(400f, 400f, 1000f, 2000f)
//        paint.style = Paint.Style.FILL
        //绘制矩形
//        canvas?.drawRect(rectf, paint)
//        paint.color = Color.BLUE
//        val rectround2 = RectF(100f, 100f, 400f, 1000f)
        //  绘制圆角矩形
//        canvas?.drawRoundRect(rectround2, 20f, 20f, paint)
//        paint.style = Paint.Style.STROKE
//        paint.color = Color.GREEN
        //绘制椭圆
//        canvas?.drawOval(rectround2, paint)
        //绘制弧线
        val arcRect = RectF(300f, 300f, 600f, 400f)
        //旋转的方向是顺时针的
        canvas?.drawArc(arcRect, 0f, 120f, false, paint)
        paint.color = Color.GREEN
        canvas?.drawArc(arcRect, 20f, 90f, true, paint)


    }

    /***
     * 可空类型的扩展函数
     */
    fun String?.isGood(): Boolean {
        return when (this) {
            null -> {
                false
            }
            else -> {
                true

            }
        }

    }
}