package com.example.homeworkcalculator.domain

import com.example.homeworkcalculator.R

enum class AppTheme(val theme: Int, val idChecked: Boolean, val key: String) {
    WHITE(R.style.CalculatorTheme, false, "white"),
    BLACK(R.style.CalculatorThemeBlack, true, "black"),
}