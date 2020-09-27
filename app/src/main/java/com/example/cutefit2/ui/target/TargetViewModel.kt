package com.example.cutefit2.ui.target

import androidx.compose.runtime.livedata.observeAsState
import com.example.cutefit2.entity.Target
import com.example.cutefit2.util.stateOf
import com.example.cutefit2.util.update
import java.util.*

class TargetViewModel {

    data class TargetViewState(
        val target: Target? = null
    )

    val state = stateOf<TargetViewState>()

    fun onSelectTargetFrom(startDate: Date) {
        state.update {
            it.copy(
                target = Target.TargetFrom(startDate = startDate)
            )
        }
    }

    fun onSelectTargetTo(endDate: Date) {
        state.update {
            it.copy(
                target = Target.TargetTo(endDate = endDate)
            )
        }
    }
}