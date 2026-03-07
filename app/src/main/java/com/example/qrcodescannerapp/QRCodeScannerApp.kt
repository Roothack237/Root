package com.example.qrcodescannerapp

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class QRCodeScannerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("DARK_MODE", false)
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
