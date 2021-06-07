package com.fahimezv.paradoxcat.data.manager

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LifeCycleManager : LifecycleObserver {

   private var mStateAppListener: StateAppListener? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppToForeground() {
        mStateAppListener?.onBackground(false)

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppToBackground() {
        mStateAppListener?.onBackground(true)


    }
    fun setOnCallback(mStateAppListener: StateAppListener) {
        this.mStateAppListener = mStateAppListener
    }

}

