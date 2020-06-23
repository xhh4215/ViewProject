package com.xh.kotlin.kotlin.viewstudyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xh.kotlin.kotlin.viewpart.viewbase.CanvasDemo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val canvasDemo = CanvasDemo(this)
        containerId.addView(canvasDemo)
    }
}
