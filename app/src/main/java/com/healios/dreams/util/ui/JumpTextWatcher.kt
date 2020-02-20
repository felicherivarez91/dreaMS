package com.healios.dreams.util.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.View


class JumpTextWatcher(private val destination: View ) : TextWatcher {

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        destination.requestFocus()
    }


}