package com.jakubveverka.spacelaunch.di

import com.jakubveverka.spacelaunch.detail.usecase.LaunchDetailUseCase
import com.jakubveverka.spacelaunch.detail.usecase.LaunchDetailUseCaseImpl
import com.jakubveverka.spacelaunch.detail.viewModel.LaunchDetailViewModel
import com.jakubveverka.spacelaunch.list.usecase.LaunchListUseCase
import com.jakubveverka.spacelaunch.list.usecase.LaunchListUseCaseImpl
import com.jakubveverka.spacelaunch.list.viewModel.LaunchListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf

import org.koin.dsl.module

val listModule = module {

    factoryOf(::LaunchDetailUseCaseImpl) { bind<LaunchDetailUseCase>() }

    factoryOf(::LaunchListUseCaseImpl) { bind<LaunchListUseCase>() }

    viewModelOf(::LaunchListViewModel)
    viewModelOf(::LaunchDetailViewModel)


}