package com.example.homeworkcalculator.model

class Model {
    val TAG = "ModelTAG"
    private var num1 = 0.0
    private var num2 = 0.0
    private var isSign = false
    private var sign = ""
    var calculationText = ""
        private set
    private var result = ""
    private val list1 = mutableListOf<Double>()
    private val list2 = mutableListOf<Double>()

    fun addDigit(a: Int) {
        calculationText += a.toString()
        if (!isSign) {
            list1.add(a.toDouble())
            num1 = makeNumber(list1)
        } else {
            list2 += a.toDouble()
            num2 = makeNumber(list2)
        }
    }

    fun addSign(s: String) {
        if (!isSign) {
            calculationText += s
            if (sign == "") sign = s
            isSign = true
        }
    }

    private fun makeNumber(array: List<Double>): Double {
        return array.reduce { acc, i -> acc * 10 + i }
    }

    fun doOperation(): String {
        result = when (sign) {
            "+" -> DoubleArithmetics.PLUS.apply(num1, num2).toString()
            "-" -> DoubleArithmetics.SUB.apply(num1, num2).toString()
            "×" -> DoubleArithmetics.MULTIPLY.apply(num1, num2).toString()
            "÷" -> {
                if (num2 == 0.0) {
                    "divide by zero not allowed"
                } else {
                    DoubleArithmetics.DIVIDE.apply(num1, num2).toString()
                }
            }
            else -> num1.toString()
        }
        return trimTrailingZero(result)
    }

    fun makeResult() {
        calculationText = trimTrailingZero(result)
        num1 = result.toDouble()
        num2 = 0.0
        isSign = false
        list1.clear()
        list1.add(num1)
        list2.clear()
    }

    private fun trimTrailingZero(value: String): String {
        return if (value.isNotEmpty()) {
            if (value.indexOf(".") < 0) {
                value
            } else {
                value.replace("0*$".toRegex(), "").replace("\\.$".toRegex(), "")
            }
        } else {
            value
        }
    }
}