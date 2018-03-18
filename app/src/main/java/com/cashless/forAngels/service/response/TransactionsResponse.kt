package com.cashless.forAngels.service.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Antonello Fodde on 17/03/18.
 * fodde.antonello@gmail.com
 */
data class TransactionsResponse(
        @SerializedName("lista") val transactionList: List<Transaction>?,
        @SerializedName("totale") val total: Int?)

data class Transaction(
        @SerializedName("data") val date: String?,
        @SerializedName("dest") val userName: String?,
        @SerializedName("importo") val amount: Int?)