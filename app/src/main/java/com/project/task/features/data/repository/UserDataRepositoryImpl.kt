package com.project.task.features.data.repository

import androidx.lifecycle.LiveData
import com.project.task.core.data_base.UserDao
import com.project.task.features.data.local.entities.UserEntity
import com.project.task.features.domain.repository.UserRepository
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(private val userDao : UserDao) : UserRepository {
    override suspend fun insertData(userEntity: UserEntity) {
        userDao.insert(userEntity)
    }

    override fun getAllData(): LiveData<List<UserEntity>> {
        return userDao.getAllData()
    }
}