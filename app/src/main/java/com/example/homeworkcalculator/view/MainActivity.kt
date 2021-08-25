package com.example.homeworkcalculator.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworkcalculator.R
import com.example.homeworkcalculator.presenter.Presenter

class MainActivity : AppCompatActivity(), Presenter.View {

    private var presenter: Presenter? = null

    private lateinit var calcTextView: TextView
    private lateinit var resultTextView: TextView
    private lateinit var clearAllBtn: Button
    private lateinit var equalBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = Presenter(this)

    }

    override fun initView() {
        calcTextView = findViewById(R.id.calc_text_view)
        resultTextView = findViewById(R.id.result_text_view)
        equalBtn = findViewById(R.id.key_equal)
        val keyZero: Button = findViewById(R.id.key_zero)
        val keyOne: Button = findViewById(R.id.key_one)
        val keyTwo: Button = findViewById(R.id.key_two)
        val keyThree: Button = findViewById(R.id.key_three)
        val keyFour: Button = findViewById(R.id.key_four)
        val keyFive: Button = findViewById(R.id.key_five)
        val keySix: Button = findViewById(R.id.key_six)
        val keySeven: Button = findViewById(R.id.key_seven)
        val keyEight: Button = findViewById(R.id.key_eight)
        val keyNine: Button = findViewById(R.id.key_nine)
        val addSign: Button = findViewById(R.id.add_sign)
        val subSign: Button = findViewById(R.id.sub_sign)
        val mulSign: Button = findViewById(R.id.mul_sign)
        val divSign: Button = findViewById(R.id.div_sign)
        clearAllBtn = findViewById(R.id.key_all_clear)

        listOf(
            keyZero, keyOne, keyTwo, keyThree, keyFour, keyFive,
            keySix, keySeven, keyEight, keyNine
        ).forEach { btn ->
            btn.setOnClickListener {
                presenter?.handlePressDigit(btn.text.toString().toInt())
                showResultTextView()
                changeButtonText()
            }
        }

        listOf(addSign, subSign, mulSign, divSign).forEach { btn ->
            btn.setOnClickListener {
                presenter?.handlePressSign(btn.text.toString())
            }
        }

        equalBtn.setOnClickListener {
            presenter?.handleEqualPress()
        }

        clearAllBtn.setOnClickListener {
            presenter?.handlePressSign("/")
        }
    }

    override fun updateCalcTextView(s: String) {
        calcTextView.text = s
    }

    override fun updateTextViewResult(s: String) {
        val str = "= $s"
        resultTextView.text = str
    }

    override fun showResultTextView() {
        if (resultTextView.visibility == View.GONE)
            resultTextView.visibility = View.VISIBLE
    }

    override fun hideResultTextView() {
        resultTextView.text = ""
        resultTextView.visibility = View.GONE
    }

    override fun changeButtonText() {
        clearAllBtn.text = "c"
    }
}