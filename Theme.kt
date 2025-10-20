package com.example.juegodecolores_examenfinal.ui.theme

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatDelegate

object JuegoDeColoresTheme {

    val primaryColor = Color.parseColor("#6650A4")
    val secondaryColor = Color.parseColor("#625B71")
    val accentColor = Color.parseColor("#EFB8C8")

    fun applyDarkTheme(context: Context, enabled: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (enabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )
    }
}
