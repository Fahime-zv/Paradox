package com.fahimezv.customebuttonlib

import android.view.ViewGroup
import android.widget.LinearLayout

class DefineParamsLayout {
    companion object {
        const val MATCH = ViewGroup.LayoutParams.MATCH_PARENT
        const val WRAP = ViewGroup.LayoutParams.WRAP_CONTENT
    }

    class Linear : LinearLayout.LayoutParams {
        constructor(width: Int, height: Int) : super(width, height)
        constructor(width: Int, height: Int, weight: Float) : super(width, height, weight)

        companion object {
            fun defaultParams() = Linear(MATCH, WRAP)
            fun wrapContent() = Linear(WRAP, WRAP)
            fun fullScreen() = Linear(MATCH, MATCH)
            fun get(w: Int, h: Int) = Linear(w, h)
        }
    }
}