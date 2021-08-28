package com.example.homeworkcalculator.presenter

import com.example.homeworkcalculator.model.Model

class Presenter(private val view: View) {

    private var model: Model = Model()

    init {
        view.initView()
    }

    fun handlePressDigit(a: Int) {
        model.addDigit(a)
        view.updateCalcTextView(model.calculationText)
        view.updateTextViewResult(model.doOperation())
    }

    fun handlePressSign(s: String) {
        model.addSign(s)
        view.updateCalcTextView(model.calculationText)
    }

    fun handleEqualPress() {
        model.makeResult()
        view.updateCalcTextView(model.calculationText)
    }

    interface View {
        fun initView()
        fun updateCalcTextView(s: String)
        fun updateTextViewResult(s: String)
        fun showResultTextView()
        fun hideResultTextView()
        fun changeButtonText()
    }
}