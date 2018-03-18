package com.cashless.forAngels.service.request

import com.google.gson.annotations.SerializedName

/**
 * Created by Antonello Fodde on 17/03/18.
 * fodde.antonello@gmail.com
 */
data class ConfirmationRequest(
        @SerializedName("id_user") val userId: Int?,
        @SerializedName("id_app") val userApp: Int?,
        @SerializedName("sum_user") val userAmount: Float?,
        @SerializedName("sum_org") val organizationAmount: Float?)