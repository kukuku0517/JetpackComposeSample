package com.example.cutefit2.entity

import java.util.*

sealed class Target {
    data class TargetFrom(
        val startDate: Date
    ):Target()

    data class TargetTo(
        val endDate: Date
    ):Target()
}