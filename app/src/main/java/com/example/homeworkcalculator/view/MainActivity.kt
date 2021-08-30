package com.example.homeworkcalculator.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworkcalculator.R
import com.example.homeworkcalculator.domain.AppTheme
import com.example.homeworkcalculator.presenter.Presenter
import com.example.homeworkcalculator.storage.ThemeStorage

class MainActivity : AppCompatActivity(), Presenter.View {
    val TAG = "MainActivityTAG"
    private var presenter: Presenter? = null

    private lateinit var calcTextView: TextView
    private lateinit var resultTextView: TextView
    private lateinit var clearAllBtn: Button
    private lateinit var equalBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val storage = ThemeStorage(this)
        val isChecked = storage.getAppTheme().idChecked
        setTheme(storage.getAppTheme().theme)

        setContentView(R.layout.activity_main)

        presenter = Presenter(this)

        val icon: CheckBox = findViewById(R.id.icon_change_theme)
        icon.isChecked = isChecked

        icon.setOnCheckedChangeListener { _, _isChecked ->
            if (!_isChecked) {
                storage.setAppTheme(AppTheme.WHITE, false)
            } else {
                storage.setAppTheme(AppTheme.BLACK, true)
            }
            recreate()
        }
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

        mapOf(
            keyZero to 0,
            keyOne to 1,
            keyTwo to 2,
            keyThree to 3,
            keyFour to 4,
            keyFive to 5,
            keySix to 6,
            keySeven to 7,
            keyEight to 8,
            keyNine to 9,
        ).forEach { (btn, value) ->
            btn.setOnClickListener {
                presenter?.handlePressDigit(value)
                showResultTextView()
                changeButtonText()
            }
        }

        mapOf(
            addSign to "+",
            subSign to "-",
            mulSign to "×",
            divSign to "÷"
        ).forEach { (btn, value) ->
            btn.setOnClickListener {
                presenter?.handlePressSign(value)
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