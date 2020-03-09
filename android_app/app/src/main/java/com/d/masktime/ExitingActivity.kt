package com.d.masktime

import android.app.Activity
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class ExitingActivity : Activity() {
    var time = System.currentTimeMillis()
    var mDate = Date(time)
    fun gettime(){
        time = System.currentTimeMillis()
        mDate = Date(time)
        var simpleDate = SimpleDateFormat("yyyy/MM/dd,hh:mm:ss")
        var time : String = simpleDate.format(mDate)
        var rDate = simpleDate.parse(time)

<<<<<<< HEAD
    }
=======


>>>>>>> 76d05021c199498aaa5eb416a5098a58d2181648
}