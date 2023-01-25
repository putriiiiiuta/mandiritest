package com.arysugiarto.mandiritest.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

/**
 * Return Color from res directory of type [Int], will return null when [Context] is Null
 * @return Color[Int]
 */
fun Context?.color(@ColorRes colorRes: Int) =
    this?.let { ContextCompat.getColor(it, colorRes) } ?: Color.TRANSPARENT

/**
 * Return Gradient/ColorStateList from res directory, will return null when [Context] is Null
 * @return [ColorStateList]
 */
fun Context?.colorStateList(@ColorRes colorRes: Int) =
    this?.let { ContextCompat.getColorStateList(it, colorRes) }
        ?: ColorStateList.valueOf(Color.TRANSPARENT)
