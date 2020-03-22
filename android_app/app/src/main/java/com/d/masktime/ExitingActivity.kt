package com.d.masktime

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.exiting_layout.*
import java.text.SimpleDateFormat
import java.util.*

class ExitingActivity : Activity() {
    var time = System.currentTimeMillis()
    var mDate = Date(time)
    val simpleDate = SimpleDateFormat("yyyy/MM/dd,HH:mm:ss")
    var settingtime = simpleDate.format(mDate)
    var total = 0L
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.exiting_layout)

        val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        var test = pref.getString("total","")

        if(test == null || test == ""){
            total = 0
        }
        else{
            val hNm = test.split(":")

            total = (hNm[0].toLong() * (60 * 60 * 1000)
                + hNm[1].toLong() * (60 * 1000)
                + hNm[2].toLong() * 1000)
        }
        val uH = total / (60 * 60 * 1000)
        val uM = (total / (60 * 1000)) % 60
        val uS = (total / 1000) % 60
        text_time_used.text = "${uH/10}${uH%10}:${uM/10}${uM%10}:${uS/10}${uS%10}"
        var button = findViewById<Button>(R.id.button_exiting)
        button.setOnClickListener{
            val uH = total / (60 * 60 * 1000)
            val uM = (total / (60 * 1000)) % 60
            val uS = (total / 1000) % 60
            getCurrentTime()
            setTime("time",settingtime)
            setTime("total","$uH:$uM:$uS")
            val next = Intent(applicationContext, OutdoorActivity::class.java)
            startActivity(next)
            finish()
        }

        //background color
        if (uH >= 6) {
            exiting_background.setBackgroundResource(R.drawable.background_red)
        }
        else if (uH >= 3) {
            exiting_background.setBackgroundResource(R.drawable.background_yellow)
        }
        else {
            exiting_background.setBackgroundResource(R.drawable.background_green)
        }

        var newbutton = findViewById<Button>(R.id.button_new_mask)
        newbutton.setOnClickListener{
            setTime("total","00:00:00")
            total = 0
            text_time_used.text = "00:00:00"
            exiting_background.setBackgroundResource(R.drawable.background_green)
        }
        val edit = pref.edit()
        edit.putBoolean("outdoor",false)
        edit.apply()
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
}