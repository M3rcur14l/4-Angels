package com.cashless.forAngels.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module


/**
 * Created by Antonello Fodde on 17/03/18.
 * fodde.antonello@gmail.com
 */
@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindContext(application: Application): Context

}