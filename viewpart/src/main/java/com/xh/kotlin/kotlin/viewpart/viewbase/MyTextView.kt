package com.xh.kotlin.kotlin.viewpart.viewbase

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class MyTextView : View {
    val paint = Paint()

    constructor(context: Context) : super(context) {
        setPaint(paint)
    }


    /***
     * 普通设置
     */
    fun setPaint(paint: Paint) {
        //设置字体宽度
        paint.strokeWidth = 3f
        //设置是否抗锯齿
        paint.isAntiAlias = true
        //设置画笔样式
        paint.style = Paint.Style.FILL
        //设置文本对齐方式
        paint.textAlign = Paint.Align.CENTER
        //设置文本大小
        paint.textSize = 40f
        //设置是否是粗体
        paint.isFakeBoldText = true
        //设置字体的底线
        paint.isUnderlineText = true
        //设置文字的倾斜度
        paint.textSkewX = -0.25f


    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.textScaleX = 2f
        canvas.drawText("欢迎光临小黑的博客", 300f, 100f, paint)
        paint.textScaleX = 1f
        paint.style = Paint.Style.STROKE
        canvas.drawText("欢迎光临小黑的博客", 300f, 300f, paint)
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.textSkewX = 0.25f
        canvas.drawText("欢迎光临小黑的博客", 300f, 500f, paint)
        paint.color = Color.RED
        val location = FloatArray(8)
        location[0] = 300f
        location[1] = 600f
        location[2] = 300f
        location[3] = 900f
        location[4] = 300f
        location[5] = 1200f
        location[6] = 300f
        location[7] = 1500f
        paint.textSize = 40f
        canvas.drawPosText("我是小黑",location,paint)


    }
}