package com.jakubveverka.spacelaunch.di

import com.jakubveverka.spacelaunch.detail.viewModel.LaunchDetailViewModel
import com.jakubveverka.spacelaunch.list.viewModel.LaunchListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listModule = module {

    viewModel { LaunchListViewModel(get()) }
    viewModel { LaunchDetailViewModel(get()) }
}