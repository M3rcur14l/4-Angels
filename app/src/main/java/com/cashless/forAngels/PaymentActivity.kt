package com.cashless.forAngels

import android.app.ProgressDialog
import android.nfc.NfcAdapter
import android.os.Bundle
import com.bumptech.glide.Glide
import com.cashless.forAngels.service.AngelService
import com.cashless.forAngels.service.response.InfoResponse
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.payment_header_layout.*
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

    private fun onData(response: InfoResponse) {
        Glide.with(userIcon)
                .load(response.userImage)
                .into(userIcon)
        userName.text = response.userName
        Glide.with(organizationIcon)
                .load(response.organizationImage)
                .into(organizationIcon)
        organizationName.text = response.organizationName

        progressDialog?.dismiss()
    }

    private fun onError(e: Throwable) {
        progressDialog?.dismiss()
    }
}