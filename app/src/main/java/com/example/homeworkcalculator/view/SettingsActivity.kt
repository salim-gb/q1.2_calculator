package com.example.homeworkcalculator.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworkcalculator.R
import com.example.homeworkcalculator.domain.AppTheme
import com.example.homeworkcalculator.storage.ThemeStorage
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsActivity : AppCompatActivity() {
    val KEY_THEME = "KEY_THEME"
    var switchValue: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val storage = ThemeStorage(this)
        switchValue = storage.getAppTheme().idChecked
        setTheme(storage.getAppTheme().theme)

        setContentView(R.layout.activity_settings_activtiy)

        val switch: SwitchMaterial = findViewById(R.id.switch_dark_theme)
        switch.isChecked = switchValue

        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {
                storage.setAppTheme(AppTheme.WHITE, false)
                recreate()
            } else {
                storage.setAppTheme(AppTheme.BLACK, true)
                recreate()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.close -> {
            val data = Intent()
            data.putExtra(KEY_THEME, switchValue)
            setResult(Activity.RESULT_OK, data)
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}