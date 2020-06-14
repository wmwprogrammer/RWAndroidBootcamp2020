package com.example.businesscards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {
    private val reminders = listOf("V = I x R", "I = V / R", "R = V / I", "P = V x I")

    internal lateinit var currentReminder: String
    private lateinit var changeReminderButton: Button
    private lateinit var reminderTextView: TextView

    private lateinit var reminderText: String
    private lateinit var initialReminderText: String

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private const val REMINDER_KEY = "REMINDER_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeReminderButton = findViewById(R.id.changeReminderButton)
        reminderTextView = findViewById(R.id.reminderTextView)

        changeReminderButton.setOnClickListener {
            reminderText = reminders.random()
            reminderTextView.text = reminderText
        }

        if (savedInstanceState != null) {
            reminderText = savedInstanceState.getString(REMINDER_KEY).toString()
        } else {
            reminderText = reminders.random()
        }
        reminderTextView.text = reminderText
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionAbout) {
            showInfo()
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(REMINDER_KEY, reminderText)
    }

    private fun showInfo() {
        val dialogTitle = getString(R.string.aboutTitle)
        val dialogMessage = getString(R.string.aboutMessage)
        val buildVersion = BuildConfig.VERSION_NAME

        val builder = AlertDialog.Builder(this)
        builder.setTitle("$dialogTitle v$buildVersion")
        builder.setMessage(dialogMessage)
        builder.create().show()
    }
}