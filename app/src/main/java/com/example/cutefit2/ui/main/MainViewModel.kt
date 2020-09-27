package com.example.cutefit2.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cutefit2.data.target.TargetRepository
import com.example.cutefit2.entity.Record
import com.example.cutefit2.util.stateOf
import com.example.cutefit2.util.update
import kotlinx.coroutines.launch

class MainViewModel(
    val targetRepository: TargetRepository
) : ViewModel() {

    data class MainViewState(
        val records: List<Record>
    )

    val state = stateOf(MainViewState(records = listOf()))

    fun loadRecords() {
        viewModelScope.launch {
            val target = targetRepository.getTarget()
            state.update {
                it.copy(records = target.records)
            }
        }
    }
}
