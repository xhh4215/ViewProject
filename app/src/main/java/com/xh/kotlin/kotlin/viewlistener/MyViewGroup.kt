package com.xh.kotlin.kotlin.viewlistener

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

class MyViewGroup(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet) {
    private val TAG = "MyViewGroup"
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(TAG, "MyViewGroup ACTION_DOWN ")

            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(TAG, "MyViewGroup ACTION_MOVE ")

            }
            MotionEvent.ACTION_UP -> {
                Log.d(TAG, "MyViewGroup ACTION_UP ")

            }
        }

        return true
    }

}