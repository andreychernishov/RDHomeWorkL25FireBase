package com.example.rdhomework25firebase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity(), OnAuthenticationLaunch {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun launch(intent: Intent) {
        TODO("Not yet implemented")
    }

    override fun showListFragment() {
        TODO("Not yet implemented")
    }

}

interface OnAuthenticationLaunch{
    fun launch(intent: Intent)
    fun showListFragment()
}