package com.jakubveverka.spacedata.repository

import com.jakubveverka.spacedata.api.ApiResult
import com.jakubveverka.spacedata.model.Launch
import kotlinx.coroutines.flow.Flow

interface SpaceRepository {

    fun getLaunches(): Flow<ApiResult<List<Launch>>>

    fun getLaunch(id: String): Flow<Launch>
}