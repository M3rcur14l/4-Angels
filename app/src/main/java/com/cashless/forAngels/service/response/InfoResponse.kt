package com.cashless.forAngels.service.response

import com.google.gson.annotations.SerializedName


/**
 * Created by Antonello Fodde on 17/03/18.
 * fodde.antonello@gmail.com
 */
data class InfoResponse(
        @SerializedName("card_id") val cardId: Int?,
        @SerializedName("org_id") val organizationId: Int?,
        @SerializedName("org_name") val organizationName: String?,
        @SerializedName("org_img") val organizationImage: String?,
        @SerializedName("org_pay") val organizationPaymentId: String?,
        @SerializedName("id_user") val userId: Int?,
        @SerializedName("user_name") val userName: String?,
        @SerializedName("user_img") val userImage: String?)