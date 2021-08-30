package com.example.homeworkcalculator.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworkcalculator.R
import com.example.homeworkcalculator.presenter.Presenter
import com.example.homeworkcalculator.storage.ThemeStorage

class MainActivity : AppCompatActivity(), Presenter.View {
    private var presenter: Presenter? = null

    private lateinit var calcTextView: TextView
    private lateinit var resultTextView: TextView
    private lateinit var clearBtn: Button
    private lateinit var equalBtn: Button
    private lateinit var eraseToLeftBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val storage = ThemeStorage(this)
        setTheme(storage.getAppTheme().theme)

        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val activityResult: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val value = result.data?.getBooleanExtra("KEY_THEME", false)
                    if (value == storage.getAppTheme().idChecked) {
                        recreate()
                    }
                }
            }

        presenter = Presenter(this)

        val settingBtn: Button = findViewById(R.id.icon_settings)
        settingBtn.setOnClickListener {
            Intent(this, SettingsActivity::class.java).also {
                activityResult.launch(it)
            }
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
        clearBtn = findViewById(R.id.key_clear)
        eraseToLeftBtn = findViewById(R.id.erase_to_the_left)

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

        clearBtn.setOnClickListener {
            presenter?.handlePressClearSign()
            hideResultTextView()
        }

        eraseToLeftBtn.setOnClickListener {
            presenter?.handlePressEraseSign()
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
}