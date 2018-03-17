package com.cashless.forAngels

import android.nfc.NfcAdapter
import android.os.Bundle
import com.cashless.forAngels.service.AngelService
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class PaymentActivity : DaggerAppCompatActivity() {

    @Inject lateinit var angelService: AngelService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        var alias = ""
        intent?.let {
            if (it.action == NfcAdapter.ACTION_NDEF_DISCOVERED)
                alias = it.data.getQueryParameter("card")
        }

    }

}