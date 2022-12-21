package com.jakubveverka.spacenavigation.di

import com.jakubveverka.spacenavigation.NavigationManager
import com.jakubveverka.spacenavigation.NavigationManagerImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val navigationModule = module {

    singleOf(::NavigationManagerImpl) { bind<NavigationManager>() }
}