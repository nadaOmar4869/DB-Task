package com.project.task.features.domain.usecase

import javax.inject.Inject

class ValidateUserInputUseCase@Inject constructor() {
    fun execute(value: String ):  ValidationResult {
        if (value.isBlank()) {
            return ValidationResult(false, "Input cannot be empty")
        }

        return ValidationResult(true)
    }
}



