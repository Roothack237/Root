package com.example.qrcodescannerapp

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val switchDarkMode = findViewById<SwitchCompat>(R.id.switchDarkMode)
        val btnClearHistory = findViewById<Button>(R.id.btnClearHistorySettings)
        val btnAbout = findViewById<Button>(R.id.btnAbout)

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "Enabled" else "Disabled"
            Toast.makeText(this, "Dark Mode $status (UI Only)", Toast.LENGTH_SHORT).show()
        }

        btnClearHistory.setOnClickListener {
            val sharedPreferences = getSharedPreferences("QR_SCAN_HISTORY", Context.MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()
            Toast.makeText(this, "Scan history cleared successfully", Toast.LENGTH_SHORT).show()
        }

        btnAbout.setOnClickListener {
            Toast.makeText(this, "QR Scanner App - Student Project", Toast.LENGTH_LONG).show()
        }
    }
}
