package com.example.homeworkcalculator.model

enum class DoubleArithmetics  {
    PLUS {
         override fun apply(a: Double, b: Double): Double = a + b
    },

    SUB {
        override fun apply(a: Double, b: Double): Double = a - b
    },

    MULTIPLY {
        override fun apply(a: Double, b: Double): Double = a * b
    },

    DIVIDE {
        override fun apply(a: Double, b: Double): Double = a / b
    };

    abstract fun apply(a: Double, b: Double): Double
}