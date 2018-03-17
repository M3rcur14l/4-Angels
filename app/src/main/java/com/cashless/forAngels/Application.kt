package com.cashless.forAngels

import com.cashless.forAngels.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


/**
 * Created by Antonello Fodde on 17/03/18.
 * fodde.antonello@gmail.com
 */
class Application : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent
            .builder()
            .application(this)
            .build()
}