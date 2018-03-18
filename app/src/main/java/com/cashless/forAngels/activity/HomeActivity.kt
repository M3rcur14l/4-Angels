package com.cashless.forAngels.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.cashless.forAngels.R
import com.cashless.forAngels.service.AngelService
import com.cashless.forAngels.service.response.TransactionsResponse
import com.cashless.forAngels.ui.TransactionListAdapter
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {

    @Inject lateinit var angelService: AngelService

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        donateButton.setOnClickListener {
            startActivity(Intent(this, ScanActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        progressDialog = ProgressDialog.show(this,
                "Un momento...", "",
                true)
        angelService.getTransactionList(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTransactions, {
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                })
    }

    @SuppressLint("SetTextI18n")
    private fun onTransactions(response: TransactionsResponse) {
        progressDialog?.dismiss()

        if (response.transactionList != null) {
            val lm = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            transactionRecycler.layoutManager = lm
            transactionRecycler.adapter = TransactionListAdapter(response.transactionList)
        }
        total.text = "€ ${response.total}"
    }

}