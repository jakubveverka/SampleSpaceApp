package com.jakubveverka.spacedata.di

import androidx.room.Room
import com.jakubveverka.spacedata.db.SpaceDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            SpaceDatabase::class.java, "SpaceDatabase"
        ).build()
    }
    single { get<SpaceDatabase>().launchDao() }
}
