package com.xh.kotlin.kotlin.viewpart.viewbase

import android.app.DirectAction
import android.content.Context
import android.graphics.*
import android.view.View
import kotlin.math.round

class PathView : View {
    //初始化一个画笔对象
    private var paint = Paint()

    constructor(context: Context) : super(context) {
        setPaint(paint)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        /***
         * 绘制路径的学习使用
         */
        var path = Path()
        //指定绘制的起点
        path.moveTo(10f, 10f)
        //绘制的终点也是下一条线的起点
        path.lineTo(40f, 40f)
        path.lineTo(100f, 40f)
        //调用这个方法形成一个闭合的环
        path.close()
        //调用drawPath绘制路径
        canvas.drawPath(path, paint)
        var pathRect = Path()
        val rectf = RectF(100f,100f,200f,600f)
        pathRect.addRect(rectf, Path.Direction.CCW)
        paint.color = Color.BLUE
        //绘制矩形路径
        canvas.drawPath(pathRect,paint)
        paint.color = Color.BLACK
        //绘制圆角矩形路径
        val roundrectf = RectF(300f,300f,500f,800f)
        val roundPath = Path()
         roundPath.addRoundRect(roundrectf,20f,10f,Path.Direction.CCW)
        canvas.drawPath(roundPath,paint)




    }


    fun setPaint(paint: Paint) {
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        paint.isAntiAlias = true

    }
}