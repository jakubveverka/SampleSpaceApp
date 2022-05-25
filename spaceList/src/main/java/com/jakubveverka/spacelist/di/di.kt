package com.jakubveverka.spacelist.di

import com.jakubveverka.spacelist.viewModel.LaunchListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listModule = module {

    viewModel { LaunchListViewModel(get()) }
}