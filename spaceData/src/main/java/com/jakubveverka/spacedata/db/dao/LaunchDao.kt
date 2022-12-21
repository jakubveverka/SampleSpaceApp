package com.jakubveverka.spacedata.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.jakubveverka.spacedata.db.model.LaunchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LaunchDao {

    @Query("SELECT * FROM LaunchEntity")
    suspend fun getAll(): List<LaunchEntity>

    @Query("DELETE FROM LaunchEntity")
    suspend fun removeAll()

    @Insert(onConflict = REPLACE)
    suspend fun insert(vararg entity: LaunchEntity)

    @Query("SELECT * FROM LaunchEntity WHERE id = :id")
    fun getLaunchById(id: String): Flow<LaunchEntity>
}