package com.missingsemicollon.pagingexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.missingsemicollon.pagingexample.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        setFragment()
    }

    private fun setFragment() {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.placeholder, PassengersFragment())
        ft.commit()
    }
}