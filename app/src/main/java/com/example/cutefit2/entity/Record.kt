package com.example.cutefit2.entity

import com.example.cutefit2.util.Time

data class Record(
    val url: String,
    val timestamp: Time,
    val message: String
)