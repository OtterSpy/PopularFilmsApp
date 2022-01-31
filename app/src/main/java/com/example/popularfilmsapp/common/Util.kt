package com.example.popularfilmsapp.common

import android.content.Context
import android.util.TypedValue
import kotlin.math.roundToInt

object Util {
    fun Context.getDp(value: Float): Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value,
        resources.displayMetrics
    ).roundToInt()
}