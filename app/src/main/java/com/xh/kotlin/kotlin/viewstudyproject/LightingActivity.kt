package com.xh.kotlin.kotlin.viewstudyproject

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_lighting.*

class LightingActivity : AppCompatActivity() {
    private lateinit var bitmap: Bitmap
    private lateinit var tempBitmap: Bitmap
    private lateinit var canvas: Canvas
    private var paint = Paint()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lighting)
        //开启硬件加速  window 下不支持关闭硬件加速
        window.setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED)
        bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.circle)
        tempBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        canvas = Canvas(tempBitmap)
        paint.isAntiAlias = true
        createBitmap(canvas)
        addColor.setOnClickListener {
            handleImageView(canvas)
            circleImageView.setImageBitmap(handleImageView(canvas))
        }
    }

    private fun createBitmap(canvas: Canvas) {
        paint.isAntiAlias = true
        canvas.drawBitmap(bitmap, 0f, 0f, paint)

    }

    private fun handleImageView(canvas: Canvas): Bitmap {
        paint.colorFilter = LightingColorFilter(0xFFFFFF, 0x0000ff)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        return tempBitmap

    }
}
