package com.cashless.forAngels

import android.content.Intent
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import it.icbpi.XPaySDK.CallBacks.FrontOfficeQPCallback
import it.icbpi.XPaySDK.Models.WebApi.Requests.FrontOffice.ApiFrontOfficeQPRequest
import it.icbpi.XPaySDK.Models.WebApi.Responses.FrontOffice.ApiFrontOfficeQPResponse
import it.icbpi.XPaySDK.Utils.EnvironmentUtils
import it.icbpi.XPaySDK.Utils.QPUtils.CurrencyUtilsQP
import it.icbpi.XPaySDK.XPay
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*


class HomeActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        testPay.setOnClickListener { pay() }
        testScan.setOnClickListener { startActivity(Intent(this, PaymentActivity::class.java)) }
    }

    private fun pay() {
        val xpay = XPay(this, BuildConfig.XPAY_SECRET)
        xpay.FrontOffice.setEnvironment(EnvironmentUtils.Environment.TEST)
        val request = ApiFrontOfficeQPRequest(
                BuildConfig.XPAY_ALIAS,
                UUID.randomUUID().toString().substring(0, 20),
                CurrencyUtilsQP.EUR,
                1000)
        xpay.FrontOffice.paga(request, object : FrontOfficeQPCallback {
            override fun onConfirm(apiFrontOfficeQPResponse: ApiFrontOfficeQPResponse) {
                if (apiFrontOfficeQPResponse.isValid) {

                } else {

                }
            }

            override fun onCancel(apiFrontOfficeQPResponse: ApiFrontOfficeQPResponse) {

            }
        })
    }
}
