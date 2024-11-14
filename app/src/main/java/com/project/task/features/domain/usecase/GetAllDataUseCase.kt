package com.project.task.features.domain.usecase

import androidx.lifecycle.LiveData
import com.project.task.features.data.local.entities.UserEntity
import com.project.task.features.domain.repository.UserRepository
import javax.inject.Inject

class GetAllDataUseCase @Inject constructor(private val repository: UserRepository) {
    operator fun invoke(): LiveData<List<UserEntity>> {
        return repository.getAllData()
    }
}