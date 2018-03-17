package com.cashless.forAngels.di.module

import com.cashless.forAngels.service.AngelService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {

    @Singleton
    @Provides
    internal fun providesAngelService(retrofit: Retrofit) = retrofit.create(AngelService::class.java)

}