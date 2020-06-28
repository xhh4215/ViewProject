package com.xh.kotlin.kotlin.viewlistener

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView

/*****
 * 利用当前的自定义View来学习View的事件的分发
 *  事件分发处理的流程
 *
 *          dispatchTouchEvent()
 *        1 - superDispatchTouchEvent     superDispatchTouchEvent()    dispatchTouchEvent()          具体的官方API的ViewGroup的事件处理逻辑
 *                  |                               |                           |                     包含是否将事件分发给ziView的 OnInterceptorTouchEvent()
 *                  |                               |                           |                                |
 *    Activity  ------- PhoneVwindow （Window）------------ DecorView  -------------------------- ViewGroup-----------  View
 *                                                                                                                       |
 *                                                                                                                       |   dispatchTouchEvent() 在这里执行View的事件分发的逻辑
 *                                                                                                                       |   OnTouchEvent() 进行事件消费的处理
 *                                                                                                                       |   return true 消费事件 并且可以接收接下来的MotionEvent
 *                                Activity -------------  DecorView   —————————————————————————— ViewGroup -----------   |   return false 不进行事件的消费  将事件的处理返回给父级视图
 *                                    |                                                                ｜
 *                             OnTouchEvent() 进行事件消费的处理                                        OnTouchEvent() 进行事件消费的处理
 *                                                                                                    return true 消费事件 并且可以接收接下来的MotionEvent
 *                                                                                                    return false 不进行事件的消费  将事件的处理返回给父级视图
 *        关于事件分发机制的结论
 *      1 同一个事件序列是由多个事件组成的 从接触屏幕的  ACTION_DOWN(开始)   -> 中间一系列事件 -> ACTION_UP(结束)
 *      2 正常情况下一个事件序列只能被一个View拦截和消耗
 *      3 某个View一旦开始处理事件，如果不消耗ACTION_DOWN事件 那么同一个事件序列的其他事件不会在交给他处理，事件会重新交给父级元素处理
 *      4 默认ViewGroup中不拦截任何事件
 *      5 View没有 OnInterceptorTouchEvent() 一但点击事件分发给他 那么他的 onTouchEvent就会被回调
 *      6 View的onTouchEvent()默认都会返回True 除非(clickable 和LongClickable同时为false)
 *      7 onClick发生的前提是View是可点击的  并且收到了  ACTION_DOWN  ACTION_UP
 *      8 可以通过requestDisallowIntercaptorTouchEvent() 在子View中干预父View的事件分发
 *      9 事件的优先级   onTouchListener（）>  onTouchEvent()  >  onClickListener()
 *      10 在View的onTouchEvent方法中只要  clickable 和 long clickable一个为 true 那么该方法就会返回true 将事件消费了
 */
class MyView(context: Context, attributeSet: AttributeSet) :
    AppCompatTextView(context, attributeSet) {
    private val TAG = "MyTextView"

    /****
     * @use
     * 这个方法是对父级视图分发下来的事件进行分发的处理逻辑，实现是通过
     * MyTextView--->TextView---->View 中进行具体的逻辑处理
     *
     * @return
     * 返回 true 表示事件会在当前的View中进行消费
     * 返回 false 表示会调用子View的dispatcheTouchEvent()进行事件的分发处理
     *
     * */
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.d(TAG, "myTextView dispatchTouchEvent ")
        return super.dispatchTouchEvent(event)
    }

    /****
     * @use
     *
     * 这个方法是对父级视图分发下来的事件的消费的具体处理逻辑的编写 会在当前View的dispatchTouchEvent()中被回调
     *
     * @return
     *
     * 此处如果返回 false 表示不消费事件 ，那么他也不会接收其他事件了，他的处理逻辑会返回给他的父级视图
     * 此处如果返回 true 表示消费当前的事件，会对一系列的touchEvent进行处理
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(TAG, "myTextView ACTION_DOWN ")

            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(TAG, "myTextView ACTION_MOVE ")

            }
            MotionEvent.ACTION_UP -> {
                Log.d(TAG, "myTextView ACTION_UP ")

            }
        }
        return false
    }
}