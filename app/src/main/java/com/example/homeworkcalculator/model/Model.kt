package com.example.homeworkcalculator.model

class Model {
    private var num1 = 0.0
    private var num2 = 0.0
    private var isSign = false
    var sign = ""
    var calculationText = ""
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
        var num = array.first()
        if (array.size > 1) {
            for (i in 1 until array.size) {
                num = num * 10 + array[i]
            }
        }
        return num
    }

    fun doOperation(): String {
        result = when (sign) {
            "+" -> sum(num1, num2).toString()
            "-" -> sub(num1, num2).toString()
            "×" -> multiply(num1, num2).toString()
            "÷" -> {
                if (num2 == 0.0) {
                    "divide by zero not allawed"
                } else {
                    divide(num1, num2).toString()
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

    private fun sum(a: Double, b: Double): Double = a + b

    private fun sub(a: Double, b: Double): Double = a - b

    private fun multiply(a: Double, b: Double): Double = a * b

    private fun divide(a: Double, b: Double): Double = a / b

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