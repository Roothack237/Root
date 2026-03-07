package com.example.qrcodescannerapp

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val switchDarkMode = findViewById<SwitchCompat>(R.id.switchDarkMode)
        val btnClearHistory = findViewById<Button>(R.id.btnClearHistorySettings)
        val btnAbout = findViewById<Button>(R.id.btnAbout)

        val sharedPreferences = getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("DARK_MODE", false)
        switchDarkMode.isChecked = isDarkMode

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("DARK_MODE", isChecked).apply()
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        btnClearHistory.setOnClickListener {
            val historyPrefs = getSharedPreferences("QR_SCAN_HISTORY", Context.MODE_PRIVATE)
            historyPrefs.edit().clear().apply()
            Toast.makeText(this, "Scan history cleared successfully", Toast.LENGTH_SHORT).show()
        }

        btnAbout.setOnClickListener {
            Toast.makeText(this, "QR Scanner App - Student Project", Toast.LENGTH_LONG).show()
        }
    }
}
