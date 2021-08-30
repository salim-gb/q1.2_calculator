package com.example.homeworkcalculator.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.homeworkcalculator.domain.AppTheme

class ThemeStorage(context: Context) {
    private val KEY_THEME = "KEY_THEME"
    private val KEY_CHECK = "KEY_CHECK"

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("theme_storage", Context.MODE_PRIVATE)

    fun setAppTheme(appTheme: AppTheme, isChecked: Boolean) {
        sharedPreferences.edit()
            .putString(KEY_THEME, appTheme.key)
            .putBoolean(KEY_CHECK, isChecked)
            .apply()
    }

    fun getAppTheme(): AppTheme {
        val value = sharedPreferences.getString(KEY_THEME, AppTheme.BLACK.key)
        for (theme in AppTheme.values()) {
            if (theme.key == value) {
                return theme
            }
        }

        return AppTheme.BLACK
    }
}