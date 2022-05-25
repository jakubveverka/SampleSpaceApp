package com.jakubveverka.samplespaceapp.di

import com.jakubveverka.samplespaceapp.navigation.NavigationManager
import com.jakubveverka.samplespaceapp.navigation.NavigationManagerImpl
import org.koin.dsl.module

val appModule = module {

    single<NavigationManager> { NavigationManagerImpl() }
}