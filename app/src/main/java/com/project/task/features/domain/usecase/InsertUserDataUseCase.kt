package com.project.task.features.domain.usecase

import com.project.task.features.data.local.entities.UserEntity
import com.project.task.features.domain.repository.UserRepository
import javax.inject.Inject


class InsertUserDataUseCase
    @Inject constructor(  val repository: UserRepository) {
    suspend operator fun invoke(userEntity: UserEntity) {
        repository.insertData(userEntity)
    }
}