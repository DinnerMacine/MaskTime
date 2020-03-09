package com.d.masktime

import android.app.Activity
import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.outdoor_layout.*
import java.util.*

class OutdoorActivity : Activity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.outdoor_layout)

        setTime("time", "2020/03/09,18:22:22")

        val setMe = getTime("time", "")

        textView.setText(setMe)
    }

    //값 저장하는 함수
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