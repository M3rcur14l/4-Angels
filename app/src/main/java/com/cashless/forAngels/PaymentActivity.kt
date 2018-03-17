package com.cashless.forAngels

import android.app.ProgressDialog
import android.nfc.NfcAdapter
import android.os.Bundle
import com.cashless.forAngels.service.AngelService
import com.cashless.forAngels.service.response.InfoResponse
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject


class PaymentActivity : DaggerAppCompatActivity() {

    @Inject lateinit var angelService: AngelService

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        progressDialog = ProgressDialog.show(this,
                "Un momento...", "",
                true)

        var alias = ""
        intent?.let {
            if (it.action == NfcAdapter.ACTION_NDEF_DISCOVERED)
                alias = it.data.getQueryParameter("card")
        }

        alias.isNotEmpty().also {
            angelService.getProfileInfo(alias)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onData, this::onError)
        }

    }

    private fun onData(respose: InfoResponse) {


        progressDialog?.dismiss()
    }

    private fun onError(e: Throwable) {
        progressDialog?.dismiss()
    }
}