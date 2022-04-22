package com.hyosik.android.pension

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.NumberPicker
import com.hyosik.android.pension.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var countDownTimer: CountDownTimer

    private val randomList = mutableListOf<Int>()

    private val random = Random()

    private var state = State.ON_STOP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        countDownTimer = object : CountDownTimer(2000 , 100L) {
            override fun onTick(millisUntilFinished: Long) {
                if(binding.articleNumberPicker.value < 5) binding.articleNumberPicker.value += 1
                else binding.articleNumberPicker.value = 1
                loopNumberPicker(binding.numberPicker1)
                loopNumberPicker(binding.numberPicker2)
                loopNumberPicker(binding.numberPicker3)
                loopNumberPicker(binding.numberPicker4)
                loopNumberPicker(binding.numberPicker5)
                loopNumberPicker(binding.numberPicker6)
            }

            override fun onFinish() {
                setNumAndState()
            }
        }
        initNumberPicker()
        initButton()
    }

    private fun initNumberPicker() = with(binding) {
        articleNumberPicker.minValue = 1
        articleNumberPicker.maxValue = 5
        setNumberPickerMinMax(numberPicker1)
        setNumberPickerMinMax(numberPicker2)
        setNumberPickerMinMax(numberPicker3)
        setNumberPickerMinMax(numberPicker4)
        setNumberPickerMinMax(numberPicker5)
        setNumberPickerMinMax(numberPicker6)
    }

    private fun initButton() = with(binding) {
        switchButton.updateIconWithState(state)
        switchButton.setOnClickListener {
            state = State.ON_PLAING
            switchButton.updateIconWithState(state)
            randomSetNumberPicker()
            countDownTimer.start()
        }
    }

    private fun setNumberPickerMinMax(numberPicker: NumberPicker) {
        numberPicker.minValue = 0
        numberPicker.maxValue = 9
    }

    private fun loopNumberPicker(numberPicker: NumberPicker) {
        if(numberPicker.value < 9) numberPicker.value += 1
        else numberPicker.value = 0
    }

    private fun randomSetNumberPicker() {
        randomList.clear()
        randomList.add(random.nextInt(5)+1)
        for(i in 0 until 6) {
            randomList.add(random.nextInt(10))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setNumAndState() = with(binding) {
        state = State.ON_STOP
        switchButton.updateIconWithState(state)

        articleNumberPicker.value = randomList[0]
        numberPicker1.value = randomList[1]
        numberPicker2.value = randomList[2]
        numberPicker3.value = randomList[3]
        numberPicker4.value = randomList[4]
        numberPicker5.value = randomList[5]
        numberPicker6.value = randomList[6]

        resultTextView.text = "${randomList[0]} 조 ${randomList[1]} ${randomList[2]} ${randomList[3]} ${randomList[4]} ${randomList[5]} ${randomList[6]}"
    }

    override fun onPause() {
        super.onPause()
        randomList.clear()
        countDownTimer.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        randomList.clear()
        countDownTimer.cancel()
    }

}