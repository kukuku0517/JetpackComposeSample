package com.example.cutefit2.entity

import com.example.cutefit2.util.Time
import java.util.*

sealed class Target {
    open val records:List<Record> = listOf()
    data class TargetFrom(
        val startDate: Time,
        override val records: List<Record> = listOf()
    ):Target()

    data class TargetTo(
        val endDate: Time,
        override val records: List<Record> = listOf()
    ):Target()
}