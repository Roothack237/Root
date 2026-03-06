package com.example.qrcodescannerapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val scannedText = intent.getStringExtra("SCAN_RESULT") ?: "No data found"
        val tvResultValue = findViewById<TextView>(R.id.tvResultValue)
        tvResultValue.text = scannedText

        // Save to History
        saveToHistory(scannedText)

        findViewById<Button>(R.id.btnCopy).setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Scanned QR Code", scannedText)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnOpenBrowser).setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(scannedText))
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Invalid URL", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnBackHome).setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }

    private fun saveToHistory(text: String) {
        val sharedPreferences = getSharedPreferences("QR_SCAN_HISTORY", Context.MODE_PRIVATE)
        val historySet = sharedPreferences.getStringSet("history_list", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        
        // Add new result to the set (SharedPreferences StringSet doesn't guarantee order)
        historySet.add("${System.currentTimeMillis()}: $text")
        
        sharedPreferences.edit().putStringSet("history_list", historySet).apply()
    }
}
