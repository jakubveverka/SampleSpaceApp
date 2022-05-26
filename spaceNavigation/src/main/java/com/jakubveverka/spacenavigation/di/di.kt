package com.jakubveverka.spacenavigation.di

import com.jakubveverka.spacenavigation.NavigationManager
import com.jakubveverka.spacenavigation.NavigationManagerImpl
import org.koin.dsl.module

val navigationModule = module {

    single<NavigationManager> { NavigationManagerImpl() }
}