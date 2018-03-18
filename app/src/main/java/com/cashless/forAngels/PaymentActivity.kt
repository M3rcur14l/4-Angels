package com.cashless.forAngels

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.nfc.NfcAdapter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.bumptech.glide.Glide
import com.cashless.forAngels.service.AngelService
import com.cashless.forAngels.service.response.InfoResponse
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import it.icbpi.XPaySDK.CallBacks.FrontOfficeQPCallback
import it.icbpi.XPaySDK.Models.WebApi.Requests.FrontOffice.ApiFrontOfficeQPRequest
import it.icbpi.XPaySDK.Models.WebApi.Responses.FrontOffice.ApiFrontOfficeQPResponse
import it.icbpi.XPaySDK.Utils.EnvironmentUtils
import it.icbpi.XPaySDK.Utils.QPUtils.CurrencyUtilsQP
import it.icbpi.XPaySDK.XPay
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.donation_button_layout.*
import kotlinx.android.synthetic.main.donation_percentage_button_layout.*
import kotlinx.android.synthetic.main.payment_header_layout.*
import java.util.*
import javax.inject.Inject


class PaymentActivity : DaggerAppCompatActivity() {

    @Inject lateinit var angelService: AngelService

    private var progressDialog: ProgressDialog? = null
    private var alias: String = ""
    private var donationPercentage: Float = 0.6f
    private var userDonationAmount: Float = 0.0f
    private var organizationDonationAmount: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        progressDialog = ProgressDialog.show(this,
                "Un momento...", "",
                true)

        var cardId = ""
        intent?.let {
            if (it.action == NfcAdapter.ACTION_NDEF_DISCOVERED)
                cardId = it.data.getQueryParameter("card")
        }

        cardId.isNotEmpty().also {
            angelService.getProfileInfo(cardId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onData, this::onError)
        }

        donationButtonSmall.setOnClickListener { onDonate(1) }
        donationButtonMedium.setOnClickListener { onDonate(2) }
        donationButtonHigh.setOnClickListener { onDonate(5) }
        donationButtonVeryHigh.setOnClickListener { onDonate(10) }

        percentageSmall.setOnClickListener {
            onPercentage(0.6f)
            percentageSmall.isEnabled = false
            percentageMedium.isEnabled = true
            percentageHigh.isEnabled = true
        }
        percentageMedium.setOnClickListener {
            onPercentage(0.7f)
            percentageSmall.isEnabled = true
            percentageMedium.isEnabled = false
            percentageHigh.isEnabled = true
        }
        percentageHigh.setOnClickListener {
            onPercentage(0.8f)
            percentageSmall.isEnabled = true
            percentageMedium.isEnabled = true
            percentageHigh.isEnabled = false
        }

        donationInput.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                donateButton.isEnabled = !s.isEmpty()
            }
        })

        donateButton.setOnClickListener { xpay(alias, userDonationAmount + organizationDonationAmount) }
    }

    private fun onPercentage(percentage: Float) {
        donationPercentage = percentage
        try {
            val amount = Integer.parseInt(donationInput.text.toString())
            userDonationAmount = amount * percentage
            organizationDonationAmount = amount * (1.0f - percentage)
            updateAmounts()
        } catch (e: Exception) {
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onDonate(amount: Int) {
        userDonationAmount = amount * donationPercentage
        organizationDonationAmount = amount * (1.0f - donationPercentage)
        donationInput.setText("$amount")
        updateAmounts()
    }

    @SuppressLint("SetTextI18n")
    private fun updateAmounts() {
        userDonationTotal.text = "$userDonationAmount €"
        organizationDonationTotal.text = "$organizationDonationAmount €"
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

        if (response.organizationPaymentId != null)
            alias = response.organizationPaymentId
        progressDialog?.dismiss()
    }

    private fun onError(e: Throwable) {
        progressDialog?.dismiss()
        Toast.makeText(this, "Errore", Toast.LENGTH_LONG).show()
    }

    private fun xpay(alias: String, amount: Float) {
        val xpay = XPay(this, BuildConfig.XPAY_SECRET)
        xpay.FrontOffice.setEnvironment(EnvironmentUtils.Environment.TEST)
        val request = ApiFrontOfficeQPRequest(
                alias,
                UUID.randomUUID().toString().substring(0, 20).replace("-", ""),
                CurrencyUtilsQP.EUR,
                (amount * 100).toLong())
        xpay.FrontOffice.paga(request, object : FrontOfficeQPCallback {
            override fun onConfirm(apiFrontOfficeQPResponse: ApiFrontOfficeQPResponse) {
                if (apiFrontOfficeQPResponse.isValid) {

                } else
                    onError(Exception("XPay Error: ${apiFrontOfficeQPResponse.error?.message}"))
            }

            override fun onCancel(apiFrontOfficeQPResponse: ApiFrontOfficeQPResponse) {
                onError(Exception("XPay Error: ${apiFrontOfficeQPResponse.error?.message}"))
            }
        })
    }
}