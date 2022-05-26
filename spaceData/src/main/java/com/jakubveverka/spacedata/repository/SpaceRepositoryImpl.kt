package com.jakubveverka.spacedata.repository

import com.jakubveverka.spacedata.api.ApiResult
import com.jakubveverka.spacedata.api.SpaceApi
import com.jakubveverka.spacedata.db.dao.LaunchDao
import com.jakubveverka.spacedata.model.Launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class SpaceRepositoryImpl(private val spaceApi: SpaceApi, private val launchDao: LaunchDao): SpaceRepository {

    override fun getLaunches() = flow {
        emit(ApiResult.Loading(listOf()))
        val dbData = launchDao.getAll()
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
                emit(ApiResult.Success(body))
                launchDao.removeAll()
                launchDao.insert(*body.toTypedArray())
            }
        } else {
            emit(ApiResult.Error("${result.code()} ${result.message()}", oldData = dbData))
        }
    }.flowOn(Dispatchers.IO)

    override fun getLaunch(id: String): Flow<Launch> = launchDao.getLaunchById(id)
}