package com.jakubveverka.spacedata.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.jakubveverka.spacedata.model.Launch
import kotlinx.coroutines.flow.Flow

@Dao
interface LaunchDao {

    @Query("SELECT * FROM Launch")
    suspend fun getAll(): List<Launch>

    @Query("DELETE FROM Launch")
    suspend fun removeAll()

    @Insert(onConflict = REPLACE)
    suspend fun insert(vararg entity: Launch)

    @Query("SELECT * FROM LAUNCH WHERE id = :id")
    fun getLaunchById(id: String): Flow<Launch>
}