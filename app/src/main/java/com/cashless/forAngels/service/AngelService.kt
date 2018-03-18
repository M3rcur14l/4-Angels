package com.cashless.forAngels.service

import com.cashless.forAngels.service.request.ConfirmationRequest
import com.cashless.forAngels.service.response.InfoResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Antonello Fodde on 17/03/18.
 * fodde.antonello@gmail.com
 */
interface AngelService {

    @GET("get_info")
    fun getProfileInfo(@Query("cc") cardId: String): Single<InfoResponse>

    @POST("confirm")
    fun confirmation(@Body request: ConfirmationRequest): Completable

}