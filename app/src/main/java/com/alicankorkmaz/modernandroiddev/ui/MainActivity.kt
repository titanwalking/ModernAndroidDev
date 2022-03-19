package com.alicankorkmaz.modernandroiddev.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alicankorkmaz.modernandroiddev.R
import com.alicankorkmaz.modernandroiddev.ui.blank.BlankFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, BlankFragment.newInstance())
            .commit()
    }
}