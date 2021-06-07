package com.fahimezv.paradoxcat

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.fahimezv.paradoxcat.data.manager.LifeCycleManager


class MyApp : Application() {

    val lifeCycleManager = LifeCycleManager()



    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(lifeCycleManager)
    }


}