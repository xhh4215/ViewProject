package com.xh.kotlin.kotlin.viewlistener

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import com.xh.kotlin.kotlin.viewstudyproject.R

class TouchActivity : AppCompatActivity() {
    private val TAG = "TouchActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d(TAG, "TouchActivity  onTouchEvent")
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(TAG, "TouchActivity ACTION_DOWN ")

            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(TAG, "TouchActivity ACTION_MOVE ")

            }
            MotionEvent.ACTION_UP -> {
                Log.d(TAG, "TouchActivity ACTION_UP ")

            }
        }
        return true
    }

}