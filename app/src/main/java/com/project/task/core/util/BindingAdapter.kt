package com.project.task.core.util

import android.text.InputType
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.BindingAdapter


@BindingAdapter("inputType")
fun setInputType(editText: AppCompatEditText, inputType: String) {
    when (inputType.lowercase()) {
        "text" -> editText.inputType = InputType.TYPE_CLASS_TEXT
        "number" -> editText.inputType = InputType.TYPE_CLASS_NUMBER

        else -> editText.inputType = InputType.TYPE_CLASS_TEXT
    }
}

@BindingAdapter("visibility")
fun visibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}
