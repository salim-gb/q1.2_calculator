package com.example.homeworkcalculator.model

class Model {
    val TAG = "ModelTAG"
    private var num1 = 0.0
    private var num2: Double? = null
    private var isSign = false
    private var sign = ""
    var calculationText = ""
        private set
    private var result = ""
    private val list1 = mutableListOf<Double>()
    private val list2 = mutableListOf<Double>()

    fun addDigit(a: Int) {
        if (a == 0 && list1.isEmpty()) {
            return
        } else {
            calculationText += a.toString()
            if (!isSign) {
                list1.add(a.toDouble())
                num1 = makeNumber(list1)
            } else {
                list2 += a.toDouble()
                num2 = makeNumber(list2)
            }
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
        if (num2 == null) {
            return trimTrailingZero(num1.toString())
        }
        result = when (sign) {
            "+" -> DoubleArithmetics.PLUS.apply(num1, num2!!).toString()
            "-" -> DoubleArithmetics.SUB.apply(num1, num2!!).toString()
            "×" -> DoubleArithmetics.MULTIPLY.apply(num1, num2!!).toString()
            "÷" -> {
                if (num2 == 0.0) {
                    "divide by zero not allowed"
                } else {
                    DoubleArithmetics.DIVIDE.apply(num1, num2!!).toString()
                }
            }
            else -> num1.toString()
        }
        return trimTrailingZero(result)
    }

    fun makeResult() {
        resetParameters()
        num1 = result.toDouble()
        calculationText = trimTrailingZero(result)
        list1.add(num1)
    }

    fun clearKeyPressed() {
        resetParameters()
    }

    fun eraseToLeft() {
        calculationText = calculationText.dropLast(1)
        if (num2 == null && isSign) {
            isSign = false
            sign = ""
            return
        }

        if (!isSign) {
            if (list1.size > 1) {
                list1.removeLast()
                num1 = makeNumber(list1)
            } else {
                clearKeyPressed()
            }
        } else {
            num2 = if (list2.size > 1) {
                list2.removeLast()
                makeNumber(list2)
            } else {
                list2.clear()
                null
            }
        }
    }

    private fun resetParameters() {
        calculationText = ""
        num1 = 0.0
        num2 = 0.0
        isSign = false
        sign = ""
        list1.clear()
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