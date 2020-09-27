package com.example.cutefit2.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

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

fun toggleOf(): MutableLiveEvent<Boolean> {
    return MutableLiveEvent<Boolean>()
}

fun <T> MutableLiveData<T>.set(copy: () -> T) {
    this.value = copy()
}

fun <T> MutableLiveData<T>.update(copy: (T) -> T) {
    this.value?.let { value ->
        this.value = (copy(value))
    }
}
//
//@Composable
//fun loadPicture(url: String): State<UiState<Bitmap>?> {
//    val state = stateOf<UiState<Bitmap>>(UiState.Loading())
//    var bitmapState = state.observeAsState()
//
//    Glide.with(ContextAmbient.current)
//        .asBitmap()
//        .load(url)
//        .into(object : CustomTarget<Bitmap>() {
//            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
//                state.set { UiState.Success(resource) }
//            }
//
//            override fun onLoadCleared(placeholder: Drawable?) {}
//        })
//
//    return bitmapState
//}
