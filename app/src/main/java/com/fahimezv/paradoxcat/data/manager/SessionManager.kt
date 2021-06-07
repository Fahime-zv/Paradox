package com.fahimezv.paradoxcat.data.manager

import android.os.Handler
import android.os.Looper
import android.util.Log

class SessionManager(lifeCycleManager: LifeCycleManager) : StateAppListener {
     val mHandler = Handler(Looper.getMainLooper())
    var mStateExpireListener: StateExpireListener? = null

    init {
        lifeCycleManager.setOnCallback(this)
        //When first state app is foreground  calling foreground
        handleForeground()
    }

    override fun onBackground(inBackground: Boolean) {
        if (inBackground) {
            handleBackground()

        } else {
            handleBackground()

        }

    }

    private fun handleForeground() {
        mHandler.postDelayed({
            Log.d(this.javaClass.simpleName, "forground")
            mStateExpireListener?.onExpire()
        }, 10000)
    }

    private fun handleBackground() {
        mHandler.removeCallbacksAndMessages(null)
        mHandler.postDelayed({
            Log.d(this.javaClass.simpleName, "background")
            mStateExpireListener?.onExpire()

        }, 30000)

    }
}