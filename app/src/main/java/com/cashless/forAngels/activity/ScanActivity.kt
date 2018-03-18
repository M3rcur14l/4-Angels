package com.cashless.forAngels.activity

import android.os.Bundle
import com.cashless.forAngels.R
import dagger.android.support.DaggerAppCompatActivity


class ScanActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
    }

}
