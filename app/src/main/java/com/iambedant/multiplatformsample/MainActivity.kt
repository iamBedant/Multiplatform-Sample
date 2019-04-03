package com.iambedant.multiplatformsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.kotlin.mpp.mobile.createApplicationScreenMessage

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val a = createApplicationScreenMessage()
    }
}
