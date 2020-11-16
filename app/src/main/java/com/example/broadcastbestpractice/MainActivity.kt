package com.example.broadcastbestpractice

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ForcsOfflineButton.setOnClickListener {
            val intent = Intent("com.example.broadcastbestpractice.FORCS_OFFLIN")
            sendBroadcast(intent)
        }
    }
}