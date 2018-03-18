package com.cashless.forAngels.ui

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cashless.forAngels.R
import com.cashless.forAngels.service.response.Transaction
import kotlinx.android.synthetic.main.row_transaction.view.*

/**
 * Created by Antonello Fodde on 18/03/18.
 * fodde.antonello@gmail.com
 */
class TransactionListAdapter(private val transactionList: List<Transaction>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_transaction, parent, false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactionList[position]
        transaction.date.let { holder.date.text = it }
        transaction.userName.let { holder.title.text = it }
        transaction.amount.let { holder.amount.text = "€ $it" }
    }

    override fun getItemCount() = transactionList.count()

}

data class ViewHolder(private val containerView: View,
                      var date: TextView = containerView.date,
                      var title: TextView = containerView.title,
                      var amount: TextView = containerView.amount) : RecyclerView.ViewHolder(containerView)