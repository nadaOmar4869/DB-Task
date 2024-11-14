package com.project.task.core.data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.task.features.data.local.entities.UserEntity

@Database(
    entities = [UserEntity::class], version = 1, exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME: String = "my_database"
    }
}