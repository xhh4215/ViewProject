package com.xh.kotlin.kotlin.viewstudyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xh.kotlin.kotlin.viewpart.viewbase.CanvasDemo
import com.xh.kotlin.kotlin.viewpart.viewbase.MyTextView
import com.xh.kotlin.kotlin.viewpart.viewbase.PathView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val canvasDemo = CanvasDemo(this)
//        val pathView = PathView(this)
        val myTextView = MyTextView(this)
        containerId.addView(myTextView)
    }
}
