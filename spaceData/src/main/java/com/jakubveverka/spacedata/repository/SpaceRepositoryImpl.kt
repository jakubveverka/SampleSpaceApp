package com.jakubveverka.spacedata.repository

import com.jakubveverka.spacedata.api.ApiResult
import com.jakubveverka.spacedata.api.SpaceApi
import com.jakubveverka.spacedata.db.dao.LaunchDao
import com.jakubveverka.spacedata.api.model.LaunchDto
import com.jakubveverka.spacedata.api.model.toDbEntity
import com.jakubveverka.spacedata.api.model.toDomain
import com.jakubveverka.spacedata.db.model.toDomain
import com.jakubveverka.spacedata.domain.model.Launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.lang.Exception

class SpaceRepositoryImpl(
    private val spaceApi: SpaceApi,
    private val launchDao: LaunchDao
) : SpaceRepository {

    override fun getLaunches() = flow {
        emit(ApiResult.Loading(listOf()))
        val dbData = launchDao.getAll().toDomain()
        emit(ApiResult.Success(dbData))
        emit(ApiResult.Loading(oldData = dbData))
        val result = try {
            spaceApi.getLaunches().execute()
        } catch (e: Exception) {
            emit(ApiResult.Error(e.message, oldData = dbData))
            return@flow
        }

        if (result.isSuccessful && result.body() != null) {
            result.body()?.let { body ->
                emit(ApiResult.Success(body.toDomain()))
                launchDao.removeAll()
                launchDao.insert(*body.toDbEntity().toTypedArray())
            }
        } else {
            emit(ApiResult.Error("${result.code()} ${result.message()}", oldData = dbData))
        }
    }.flowOn(Dispatchers.IO)

    override fun getLaunch(id: String): Flow<Launch> = launchDao.getLaunchById(id).map { it.toDomain() }
}