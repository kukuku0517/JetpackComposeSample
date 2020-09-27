package com.example.cutefit2.ui.target

import androidx.compose.runtime.livedata.observeAsState
import com.example.cutefit2.data.target.TargetRepository
import com.example.cutefit2.entity.Target
import com.example.cutefit2.util.*
import java.util.*

class TargetViewModel(
    val targetRepository: TargetRepository
) {

    data class TargetViewState(
        val target: Target
    )

    val state = stateOf(
        TargetViewState(Target.TargetFrom(Time()))
    )

    val completeEvent = toggleOf()

    fun onSelectTargetFrom(startDate: Time) {
        state.update {
            it.copy(
                target = Target.TargetFrom(startDate = startDate)
            )
        }
    }

    fun onSelectTargetTo(endDate: Time) {
        state.update {
            it.copy(
                target = Target.TargetTo(endDate = endDate)
            )
        }
    }

    fun confirm() {
        completeEvent.value = true
    }
}