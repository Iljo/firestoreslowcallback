package com.example.firestoreslowcallback

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.MetadataChanges
import kotlinx.android.synthetic.main.activity_main.*

const val LOG_TAG = "FirestoreIssue"

class MainActivity : AppCompatActivity() {

    private val firestoreDb = FirebaseFirestore.getInstance()

    private var registration: ListenerRegistration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        datePickerView.init(2020, 3 - 1, 20) { _, year, month, dayOfMonth -> dateSelected(year, month + 1, dayOfMonth) }
        with(datePickerView) { dateSelected(year, month + 1, dayOfMonth) }
    }

    private fun dateSelected(year: Int, month: Int, day: Int) {
        val formattedDate = "$year-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}"
        registration?.remove()

        Log.d(LOG_TAG, "Selected $formattedDate, waiting for response...")
        registration = firestoreDb
            .collection("notes")
            .document(formattedDate)
            .addSnapshotListener(MetadataChanges.INCLUDE) { snapshot, exception ->
                Log.d(LOG_TAG, "Response: ${snapshot?.metadata}")

                if (exception != null) {
                    Log.e(LOG_TAG, "Error occurred", exception)
                    return@addSnapshotListener
                }

                if (snapshot == null || !snapshot.exists()) {
                    textView.text = ""
                    return@addSnapshotListener
                }

                val note = snapshot.get("note") as String
                textView.text = note
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        registration?.remove()
    }
}
