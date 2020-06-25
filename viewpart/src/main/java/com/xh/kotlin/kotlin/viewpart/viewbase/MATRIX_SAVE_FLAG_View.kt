package com.xh.kotlin.kotlin.viewpart.viewbase

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MATRIX_SAVE_FLAG_View (context: Context, attributeSet: AttributeSet) : View(context, attributeSet){
    var paint = Paint()
    init {
        setLayerType(LAYER_TYPE_SOFTWARE,null)
        paint.color = Color.GREEN
    }

    /***
     * 无论哪个图层调用canvas的位置变换和裁剪操作，所有的画布都会受到连累，这些连累只表现在以后的绘图上
     * ，之前的绘图是不会受到影响的
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //画板对象默认保存相应的位置信息
        canvas.save()
        //旋转画布
        canvas.rotate(40f)
        //画布的裁剪操作是没法恢复的 因为 画布保存的只是位置信息 画布的大小信息没法保存
//        canvas.clipRect(100f,0f,200f,100f)
        canvas.drawRect(100f,0f,200f,100f,paint)
        //恢复到修改之前的状态
        canvas.restore()
        paint.color = Color.RED
        canvas.drawRect(100f,0f,200f,100f,paint)

    }
}