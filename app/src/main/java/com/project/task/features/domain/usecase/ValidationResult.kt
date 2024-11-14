package com.project.task.features.domain.usecase

data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String? = null
)