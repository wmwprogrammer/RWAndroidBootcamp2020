package com.example.businesscards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val reminders = listOf("V = I x R", "I = V / R", "R = V / I", "P = V x I")

//    private var currentReminder: String = ""
    private var reminderText: String = ""
//    private var initialReminderText: String = ""

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private const val REMINDER_KEY = "REMINDER_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeReminderButton.setOnClickListener {
            reminderText = reminders.random()
            reminderTextView.text = reminderText
        }

        reminderText = savedInstanceState?.getString(REMINDER_KEY) ?: reminders.random()
        reminderTextView.text = reminderText
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionAbout) {
            showBuildInfoDialog()
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(REMINDER_KEY, reminderText)
        super.onSaveInstanceState(outState)
    }

    private fun showBuildInfoDialog() {
        val dialogTitle = getString(R.string.aboutTitle)
        val dialogMessage = getString(R.string.aboutMessage)
        val buildVersion = BuildConfig.VERSION_NAME

        val builder = AlertDialog.Builder(this)
        builder.setTitle("$dialogTitle v$buildVersion")
        builder.setMessage(dialogMessage)
        builder.create().show()
    }
}
