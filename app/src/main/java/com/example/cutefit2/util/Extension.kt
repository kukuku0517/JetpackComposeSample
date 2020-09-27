package com.example.cutefit2.util

import android.content.Context
import androidx.annotation.ColorRes
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData

class Extension {
}

fun Any.simpleTag(): String {
    return this.javaClass.simpleName
}

fun Context.color(@ColorRes id: Int): Color {
    return Color(ContextCompat.getColor(this, id))
}


fun <T> stateOf(init: T? = null): MutableLiveData<T> {
    return MutableLiveData<T>().apply {
        init?.let {
            value = (it)
        }
    }
}

fun <T> eventOf(default: T? = null): MutableLiveEvent<T> {
    return MutableLiveEvent<T>().apply {
        default?.let {
            value = it
        }
    }
}

fun <T> MutableLiveData<T>.set(value: T) {
    this.value = value
}

fun <T> MutableLiveData<T>.update(copy: (T) -> T) {
    this.value?.let { value ->
        this.value = (copy(value))
    }
}

