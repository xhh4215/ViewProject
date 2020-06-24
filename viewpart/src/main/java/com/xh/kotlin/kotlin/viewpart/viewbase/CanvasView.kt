package com.xh.kotlin.kotlin.viewpart.viewbase

import android.content.Context
import android.graphics.*
import android.view.View

/***
 * /****
 * paint 对象的api总结使用
 *
 * reset()   重置画笔
 *
 * setColor(int Color) 设置画笔的颜色
 *
 * setARGB(int a ,int r,int g,int b) 通过argb设置画笔的颜色
 *
 * setAlpha(int a) 设置画笔的透明度
 *
 * setStyle(Paint.Style style) 设置画笔的样式
 *
 * setStrokeWidth(float width) 设置画笔的宽度
 *
 * setAntiAlias(boolean aa) 设置画笔是否抗锯齿
 *
 * setStrokeCap(Paint.Cap cap) 设置线冒样式，取值有Cap.ROUND(圆形线冒)、Cap.SQUARE(方形线冒)、Paint.Cap.BUTT(无线冒)
 *
 * setStrokeJoin(Paint.Join join)  设置线段连接处样式，取值有：Join.MITER（结合处为锐角）、Join.Round(结合处为圆弧)、Join.BEVEL(结合处为直线)
 *
 * setPathEffect(PathEffect effect) 设置路径样式;取值类型是所有派生自PathEffect的子类：ComposePathEffect, CornerPathEffect, DashPathEffect, DiscretePathEffect, PathDashPathEffect, SumPathEffect
*/
 */
class CanvasView(context: Context) : View(context) {
    val paint = Paint()

    init {
        paint.color = Color.GREEN
        paint.isAntiAlias = true
        paint.strokeWidth = 12f
        paint.style = Paint.Style.STROKE

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        val rect = Rect(100, 100, 600, 300)
//        canvas.drawRect(rect, paint)
        //平移画布
//        canvas.translate(100f,100f)
        //旋转画布
//        canvas.rotate(20f)
        //指定旋转的中心点
//        canvas.rotate(20f, 100f, 100f)
        //缩放操作
//        canvas.scale(1f,2f)
        //扭曲
//        canvas.skew(1.723f, 0f)
//        paint.color = Color.RED
//        canvas.drawRect(rect, paint)
//        canvas.drawColor(Color.RED)
//        canvas.clipRect(Rect(100, 100, 1000, 1000))
//        canvas.drawColor(Color.GREEN)
//          canvas.drawColor(Color.RED)
//          canvas.save()
//          canvas.clipRect( Rect(10,10,100,100))
//          canvas.restore()
//          canvas.drawColor(Color.BLUE)
        //画笔的线帽的使用
//        paint.mySetStrokeCap(1)
//        paint.mySetStrokeCap(2)
//        paint.mySetStrokeCap(3)
//        canvas.drawLine(100f, 300f, 1000f, 300f, paint)

//        paint.mySetStrokeJoin(1)
        paint.mySetStrokeJoin(2)
//        paint.mySetStrokeJoin(3)
        var path = Path()
        //通过 moveTo()设置路径的起始点
        path.moveTo(100f, 100f)
        //通过 lineTo() 设置结束点
        path.lineTo(100f, 300f)
        path.lineTo(300f, 300f)
        canvas.drawPath(path, paint)

    }

    /***
     * 设置paint的线帽
     */
    fun Paint.mySetStrokeCap(type: Int) {
        when (type) {
            //圆形线帽
            1 -> this.strokeCap = Paint.Cap.ROUND
            //方形线帽
            2 -> this.strokeCap = Paint.Cap.SQUARE
            //无线帽
            3 -> this.strokeCap = Paint.Cap.BUTT

        }
    }

    /***
     * 设置paint连接处的样式
     */
    fun Paint.mySetStrokeJoin(type: Int) {
        when (type) {
            //结合处是锐角
            1 -> this.strokeJoin = Paint.Join.MITER
            //结合处为圆弧
            2 -> this.strokeJoin = Paint.Join.ROUND
            //结合处为直角
            3 -> this.strokeJoin = Paint.Join.BEVEL
        }

    }

    /***
     * 设置路径样式;取值类型是所有派生自PathEffect的子类
     *
     * 1 CornerPathEffect——圆形拐角效果
     * 2 DashPathEffect——虚线效果
     * 3 DiscretePathEffect——离散路径效果
     * 4 PathDashPathEffect——印章路径效果
     */
    fun Paint.mySetPathEffec(type: Int){

    }
}