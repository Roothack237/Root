package com.example.qrcodescannerapp

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {

    private lateinit var lvHistory: ListView
    private lateinit var btnClearHistory: Button
    private lateinit var adapter: ArrayAdapter<String>
    private val historyList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        lvHistory = findViewById(R.id.lvHistory)
        btnClearHistory = findViewById(R.id.btnClearHistory)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, historyList)
        lvHistory.adapter = adapter

        loadHistory()

        btnClearHistory.setOnClickListener {
            clearHistory()
        }
    }

    private fun loadHistory() {
        val sharedPreferences = getSharedPreferences("QR_SCAN_HISTORY", Context.MODE_PRIVATE)
        val historySet = sharedPreferences.getStringSet("history_list", emptySet()) ?: emptySet()
        
        historyList.clear()
        // Sort by timestamp (descending) and clean up the display string
        val sortedList = historySet.toList().sortedDescending()
        for (item in sortedList) {
            val cleanItem = if (item.contains(": ")) item.substringAfter(": ") else item
            historyList.add(cleanItem)
        }
        
        adapter.notifyDataSetChanged()

        if (historyList.isEmpty()) {
            Toast.makeText(this, "No scan history available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearHistory() {
        val sharedPreferences = getSharedPreferences("QR_SCAN_HISTORY", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
        
        historyList.clear()
        adapter.notifyDataSetChanged()
        Toast.makeText(this, "History cleared", Toast.LENGTH_SHORT).show()
    }
}
