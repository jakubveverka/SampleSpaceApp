package com.jakubveverka.spacedata.di

import androidx.room.Room
import com.jakubveverka.spacedata.api.SpaceApi
import com.jakubveverka.spacedata.db.SpaceDatabase
import com.jakubveverka.spacedata.repository.SpaceRepository
import com.jakubveverka.spacedata.repository.SpaceRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            SpaceDatabase::class.java, "SpaceDatabase"
        ).build()
    }
    single { get<SpaceDatabase>().launchDao() }

    single {
        Retrofit.Builder()
            .baseUrl(SPACE_X_DATA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpaceApi::class.java)
    }

    factoryOf(::SpaceRepositoryImpl) { bind<SpaceRepository>() }
}

private const val SPACE_X_DATA_URL = "https://api.spacexdata.com/"