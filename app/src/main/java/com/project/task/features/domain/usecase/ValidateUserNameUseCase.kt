package com.project.task.features.domain.usecase

import javax.inject.Inject

class ValidateUserNameUseCase@Inject constructor() {
    fun execute(value: String):  ValidationResult {
        if (value.isBlank()) {
            return ValidationResult(false, "Name cannot be empty")
        }
        if (value.length < 10) {
            return ValidationResult(false, "Name must be at least 10 characters long.")
        }

        return ValidationResult(true)
    }
}

