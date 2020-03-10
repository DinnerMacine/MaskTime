package com.d.masktime

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import java.text.SimpleDateFormat
import java.util.*


class OutdoorActivity : Activity() {
    var currentTime = System.currentTimeMillis()
    var mDate = Date(currentTime)
    val simpleDate = SimpleDateFormat("yyyy/MM/dd,hh:mm:ss")
    var ScompareMe = simpleDate.format(mDate)
    val timer = Timer()
    lateinit var tOut :String
    var total :Long = 0L
    lateinit var string  : String

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.outdoor_layout)
        var button = findViewById<Button>(R.id.TimeButton)

        val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val test = pref.getString("total","")
        Log.v("test","$test")
        if(test != "" && test != null){
            val hNm = test.split(":")
            total = hNm[0].toLong() * (60 * 60 * 1000)
                    + hNm[1].toLong() * (60 * 1000)
                    + hNm[2].toLong() * 1000
        }

        val test2 = pref.getString("time","")
        tOut = if(test2 == "" || test == null){
            Log.v("error","무언가 잘못되엇습니다!")
            ScompareMe
        } else{
            test2!!
        }

        val TT: TimerTask = object : TimerTask() {
            override fun run() { // 반복실행할 구문
                currentTime = System.currentTimeMillis()
                mDate = Date(currentTime)
                ScompareMe = simpleDate.format(mDate)
                calculateTime()
                button.post {
                    button.text = string
                }
            }
        }
        timer.schedule(TT,0,1000)

        Log.v("total","" + total)
        Log.v("out","" + simpleDate.parse(tOut))


        val compareMe1 = simpleDate.parse(ScompareMe)
        val compareMe2 = simpleDate.parse(tOut)
        var used = total + compareMe1.time - compareMe2.time
        val h = used / (60  * 60 * 1000)
        val m = (used / (60 * 1000)) % 60
        val s = (used / 1000) % 60

        string = "$h:$m:$s"
        button.text = string

        button.setOnClickListener{
            calculateTime()
            setTime("total",string)
            val next = Intent(this,ExitingActivity::class.java)
            startActivity(next)
            finish()
        }
    }

    //값 저장하는 함수
    fun setTime(key: String, value: String) {
        val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val edit = pref.edit()
        edit.putString(key, value)
        edit.apply()
    }

    fun calculateTime(){
        val compareMe1 = simpleDate.parse(ScompareMe)
        val compareMe2 = simpleDate.parse(tOut)
        var used = total + compareMe1.time - compareMe2.time
        val h = used / (60  * 60 * 1000)
        val m = (used / (60 * 1000)) % 60
        val s = (used / 1000) % 60

        string = "$h:$m:$s"
    }

}