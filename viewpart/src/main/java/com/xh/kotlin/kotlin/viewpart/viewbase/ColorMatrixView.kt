package com.xh.kotlin.kotlin.viewpart.viewbase

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.xh.kotlin.kotlin.viewpart.R

class ColorMatrixView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    var paint = Paint()
    lateinit var bitmap: Bitmap

    init {
        paint.isAntiAlias = true
        paint.setARGB(255, 200, 100, 100)
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.smallpz)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        canvas.drawRect(100f, 100f, 300f, 300f, paint)
        canvas.drawBitmap(bitmap,null,Rect(0,0,bitmap.width/5,bitmap.height/5),paint)
        //移动画布
//        canvas.translate(600f, 0f)
        //相当于将红色和绿色的色道过滤掉了
//        var colorMatrix= ColorMatrix(
//           floatArrayOf(
//               1.5f,0f,0f,0f,0f,
//               0f,0f,0f,0f,50f,
//               0f,0f,0f,0f,0f,
//               0f,0f,0f,1f,0f
//           )
//        )
//        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
//        canvas.drawRect(100f, 100f, 300f, 300f, paint)
//        canvas.drawBitmap(bitmap,null,Rect(0,0,bitmap.width/5,bitmap.height/5),paint)


    }
}