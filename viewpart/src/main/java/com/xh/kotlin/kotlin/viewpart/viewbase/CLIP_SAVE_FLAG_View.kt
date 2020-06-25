package com.xh.kotlin.kotlin.viewpart.viewbase

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CLIP_SAVE_FLAG_View(context: Context, attributeSet: AttributeSet) : View(context, attributeSet){
    var paint = Paint()
    init {
        setLayerType(LAYER_TYPE_SOFTWARE,null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.RED)
        canvas.save()
        canvas.clipRect(100f,0f,200f,100f)
        canvas.restore()
        canvas.drawColor(Color.YELLOW)
    }
}