package com.project.task.features.domain.repository

import androidx.lifecycle.LiveData
import com.project.task.features.data.local.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertData(userEntity: UserEntity)

    fun getAllData(): LiveData<List<UserEntity>>
}