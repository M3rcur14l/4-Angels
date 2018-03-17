package com.cashless.forAngels

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import it.icbpi.XPaySDK.XPay
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {

    @Inject lateinit var xpay: XPay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        testButton.setOnClickListener { pay() }
    }

    private fun pay() {

    }
}
