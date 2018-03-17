package com.cashless.forAngels.service.response


/**
 * Created by Antonello Fodde on 17/03/18.
 * fodde.antonello@gmail.com
 */
data class InfoResponse(
        val cardId: Int?,
        val organizationId: Int?,
        val organizationName: String?,
        val organizationImage: String?,
        val organizationPaymentId: String?,
        val userId: Int?,
        val userName: String?,
        val userImage: String?)