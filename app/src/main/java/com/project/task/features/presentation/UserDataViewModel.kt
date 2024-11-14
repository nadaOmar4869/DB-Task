package com.project.task.features.presentation

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.task.features.data.local.entities.UserEntity
import com.project.task.features.domain.usecase.GetAllDataUseCase
import com.project.task.features.domain.usecase.InsertUserDataUseCase
import com.project.task.features.domain.usecase.ValidateUserAgeUseCase
import com.project.task.features.domain.usecase.ValidateUserInputUseCase
import com.project.task.features.domain.usecase.ValidateUserNameUseCase
import com.project.task.features.domain.usecase.ValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDataViewModel
@Inject constructor(
    private val insertUserDataUseCase: InsertUserDataUseCase,
    private val getAllDataUseCase: GetAllDataUseCase,
    private val validateUserInputUseCase: ValidateUserInputUseCase,
    private val validateUserNameUseCase: ValidateUserNameUseCase,
    private val validateUserAgeUseCase: ValidateUserAgeUseCase,
) : ViewModel() {
    val nameTextFieldData = MutableLiveData<String>()
    val ageTextFieldData = MutableLiveData<String>()
    val jobTitleTextFieldData = MutableLiveData<String>()
    val genderTextFieldData = MutableLiveData<String>()

    val nameError = MutableLiveData<String?>()
    val ageError = MutableLiveData<String?>()
    val jobTitleError = MutableLiveData<String?>()
    val genderError = MutableLiveData<String?>()


    val isSaveBtnEnable = MutableLiveData(false)

    private val fieldsMediatorLiveData = MediatorLiveData<Boolean>()

    init {
        // Adding all fields to the MediatorLiveData observer
        fieldsMediatorLiveData.addSource(nameTextFieldData) { checkFields() }
        fieldsMediatorLiveData.addSource(ageTextFieldData) { checkFields() }
        fieldsMediatorLiveData.addSource(jobTitleTextFieldData) { checkFields() }
        fieldsMediatorLiveData.addSource(genderTextFieldData) { checkFields() }

        fieldsMediatorLiveData.observeForever {
            isSaveBtnEnable.value = it
        }
    }


    fun getAllDat() = getAllDataUseCase()

    fun insertData(
        onCallBack: () -> Unit
    ) {
        var isValid = true

        // Validate Data
        validateAndSetError(
            nameTextFieldData.value.toString(),
            validateUserNameUseCase::execute,
            nameError
        ).also { if (!it) isValid = false }
        validateAndSetError(
            ageTextFieldData.value.toString(),
            validateUserAgeUseCase::execute,
            ageError
        ).also { if (!it) isValid = false }
        validateAndSetError(
            jobTitleTextFieldData.value.toString(),
            validateUserInputUseCase::execute,
            jobTitleError
        ).also { if (!it) isValid = false }
        validateAndSetError(
            genderTextFieldData.value.toString(),
            validateUserInputUseCase::execute,
            genderError
        ).also { if (!it) isValid = false }



        if (isValid) {
            viewModelScope.launch {
                insertUserDataUseCase.invoke(
                    UserEntity(
                        name = nameTextFieldData.value.toString(),
                        jobTitle = jobTitleTextFieldData.value.toString(),
                        gender = genderTextFieldData.value.toString(),
                        age = ageTextFieldData.value.toString().toInt()
                    )
                )
                onCallBack()
            }
        }

    }

    private fun checkFields() {
        val isAllFieldsValid = !nameTextFieldData.value.isNullOrEmpty() &&
                !ageTextFieldData.value.isNullOrEmpty() &&
                !jobTitleTextFieldData.value.isNullOrEmpty() &&
                !genderTextFieldData.value.isNullOrEmpty()

        isSaveBtnEnable.value = isAllFieldsValid
    }

    private fun validateAndSetError(
        fieldValue: String,
        validator: (String) -> ValidationResult,
        errorLiveData: MutableLiveData<String?>
    ): Boolean {
        val result = validator(fieldValue)
        if (result.isValid) {
            errorLiveData.value = null
            return true
        } else {
            errorLiveData.value = result.errorMessage
            return false
        }
    }
}