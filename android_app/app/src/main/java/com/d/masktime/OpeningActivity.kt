package com.d.masktime

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

class OpeningActivity : Activity() {
    lateinit var next_activity : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val outdoor_now = pref.getBoolean("outdoor", false)

        if (outdoor_now) {
            next_activity = Intent(applicationContext, OutdoorActivity::class.java)
        }
        else {
            next_activity = Intent(applicationContext, ExitingActivity::class.java)
        }

        startActivity(next_activity)
        finish()
    }
}