package com.project.task.features.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val jobTitle: String,
    val gender: String,
    val age: Int,
)
