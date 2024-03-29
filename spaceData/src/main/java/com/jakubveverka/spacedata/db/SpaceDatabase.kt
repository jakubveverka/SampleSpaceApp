package com.jakubveverka.spacedata.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jakubveverka.spacedata.db.dao.LaunchDao
import com.jakubveverka.spacedata.db.model.LaunchEntity

@Database(entities = [LaunchEntity::class], version = 1)
abstract class SpaceDatabase : RoomDatabase() {

    abstract fun launchDao(): LaunchDao
}