package com.project.task.features.domain.usecase

import javax.inject.Inject


class ValidateUserAgeUseCase @Inject constructor(){
    fun execute(age: String ):  ValidationResult {
        return when {
            age.isBlank() -> ValidationResult(false, "Age cannot be empty")
            !isNumber(age) -> ValidationResult(false, "Age must be a number")
            age.toInt() == 0 -> ValidationResult(false, "Age cannot be 0")
            else -> ValidationResult(true)
        }
    }

    private fun isNumber(value: String): Boolean {
        return value.isEmpty() || Regex("^\\d+\$").matches(value)
    }
}
