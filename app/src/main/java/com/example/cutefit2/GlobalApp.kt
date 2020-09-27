package com.example.cutefit2

import android.app.Application
import com.example.cutefit2.data.target.TargetRepository
import com.example.cutefit2.data.target.TargetRepositoryImpl
import com.example.cutefit2.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class GlobalApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    repository,
                    viewModel
                )
            )
        }
    }
}

val repository = module {
    single<TargetRepository> { TargetRepositoryImpl() }
}

val viewModel = module {
    viewModel { MainViewModel(get()) }
}