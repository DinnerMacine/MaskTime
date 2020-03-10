package com.d.masktime

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.exiting_layout.*
import java.text.SimpleDateFormat
import java.util.*

class ExitingActivity : Activity() {
    var time = System.currentTimeMillis()
    var mDate = Date(time)
    val simpleDate = SimpleDateFormat("yyyy/MM/dd,hh:mm:ss")
    var settingtime = simpleDate.format(mDate)
    var total = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exiting_layout)
        var test = getTime("total","")
        if(test == null || test == ""){
            total = 0
        }
        else{
            val hNm = test.split(":")
            total = hNm[0].toLong() * (60 * 60 * 1000) + hNm[1].toLong() * (60 * 1000)
        }
        val uH = total / (60 * 60 * 1000)
        val uM = (total / (60 * 1000)) % 60
        text_time_used.text = "$uH:$uM"
        var button = findViewById<Button>(R.id.button_exiting)
        button.setOnClickListener{
            getCurrentTime()
            setTime("time",settingtime)
            val next = Intent(applicationContext, OutdoorActivity::class.java)
            startActivity(next)
        }
        var newbutton = findViewById<Button>(R.id.button_new_mask)
        newbutton.setOnClickListener{
            setTime("total","0:0")
            total = 0
            text_time_used.text = "0:0"
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