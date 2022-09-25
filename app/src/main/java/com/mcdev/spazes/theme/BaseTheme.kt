package com.mcdev.spazes.theme

import android.content.Context
import com.dolatkia.animatedThemeManager.AppTheme

interface BaseTheme : AppTheme {
    fun textColor(): Int

    //cards
    fun cardBg(): Int

    fun activityBgColor(context: Context): Int

    fun statusBarColor(): Int

    fun lottieColor(): Int

    // multi search
    fun searchTextColor(context: Context): Int
    fun searchSelectedTabColor(context: Context): Int
    fun searchClearIconColor(context: Context): Int
    fun searchHintColor(context: Context): Int
    fun searchIconColor(context: Context): Int
}