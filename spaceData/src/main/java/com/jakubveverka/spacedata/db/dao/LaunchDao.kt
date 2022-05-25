package com.jakubveverka.spacedata.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.jakubveverka.spacedata.api.Launch
import kotlinx.coroutines.flow.Flow

@Dao
interface LaunchDao {

    @Query("SELECT * FROM LaunchEntity")
    fun getAll(): Flow<List<Launch>>

    @Query("DELETE FROM LAUNCHENTITY")
    suspend fun removeAll()

    @Insert(onConflict = REPLACE)
    suspend fun insert(vararg entity: Launch)
}