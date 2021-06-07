package com.fahimezv.customebuttonlib

import android.graphics.Color
import android.graphics.drawable.GradientDrawable


class RectangleShape(pStrokeColor: Int, cornerRadius: Float) :
    GradientDrawable(Orientation.BOTTOM_TOP, intArrayOf(Color.WHITE, Color.WHITE, Color.WHITE)) {
    init {
        setColor(pStrokeColor)
        shape = RECTANGLE
        setCornerRadius(cornerRadius)
    }
}