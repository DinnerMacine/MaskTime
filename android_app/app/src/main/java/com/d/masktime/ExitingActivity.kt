package com.d.masktime

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import java.text.SimpleDateFormat
import java.util.*

class ExitingActivity : Activity() {
    var time = System.currentTimeMillis()
    var mDate = Date(time)
    val simpleDate = SimpleDateFormat("yyyy/MM/dd,hh:mm:ss")
    var settingtime = simpleDate.format(mDate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exiting_layout)

        var button = findViewById<Button>(R.id.Exitbutton)
        button.setOnClickListener{
            getCurrentTime()
            setTime("time",settingtime)
            val next = Intent(applicationContext, OutdoorActivity::class.java)
            startActivity(next)
        }
    }

    fun getCurrentTime() {
        time = System.currentTimeMillis()
        mDate = Date(time)
        settingtime = simpleDate.format(mDate)
    }

    fun setTime(key: String, value: String) {
        val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val edit = pref.edit()
        edit.putString(key, value)
        edit.apply()
    }

    //값 불러오는 함수
    fun getTime(key: String, value: String) : String? {
        val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        return pref.getString(key, value)
    }
}