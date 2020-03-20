package com.example.firestoreslowcallback

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.Locale

const val LOG_TAG = "FirestoreIssue"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        datePickerView.init(2020, 3, 20) { _, year, month, dayOfMonth -> dateSelected(year, month, dayOfMonth) }
        with(datePickerView) { dateSelected(year, month, dayOfMonth) }
    }

    private fun dateSelected(year: Int, month: Int, day: Int) {
        val formattedDate = "$year-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}"
        Log.d(LOG_TAG, formattedDate)
    }
}
