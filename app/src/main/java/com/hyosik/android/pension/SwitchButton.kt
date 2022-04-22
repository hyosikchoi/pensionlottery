package com.hyosik.android.pension

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton

class SwitchButton(
    context: Context,
    attrs : AttributeSet
) : AppCompatImageButton(context,attrs){

    fun updateIconWithState(state: State) {
        when(state) {
            State.ON_STOP -> {
                setImageResource(R.drawable.ic_power_off)
            }
            State.ON_PLAING -> {
                setImageResource(R.drawable.ic_power_on)
            }
        }
    }

}