package com.cashless.forAngels.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.cashless.forAngels.R
import com.google.zxing.integration.android.IntentIntegrator
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_scan.*

class ScanActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        qrCode.setOnClickListener { IntentIntegrator(this).initiateScan() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null)
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            else {
                val intent = Intent(this, PaymentActivity::class.java)
                intent.putExtra(PaymentActivity.CARD_ID, result.contents)
                startActivity(intent)
            }
        } else
            super.onActivityResult(requestCode, resultCode, data)
    }
}
