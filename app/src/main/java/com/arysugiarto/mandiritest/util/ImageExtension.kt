package com.arysugiarto.mandiritest.util

import android.content.Context
import android.graphics.drawable.GradientDrawable
import androidx.annotation.ColorRes
import com.arysugiarto.mandiritest.R

fun Context.createCircleDrawable(
    whSize: Pair<Int,Int>,
    @ColorRes backgroundColor: Int = R.color.black
): GradientDrawable {
    return GradientDrawable().apply {
        shape = GradientDrawable.OVAL
        cornerRadii = floatArrayOf(0f,0f,0f,0f,0f,0f,0f,0f)
        color = colorStateList(backgroundColor)
        setSize(whSize.first, whSize.second)
    }
}
