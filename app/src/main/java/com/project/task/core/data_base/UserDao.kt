package com.project.task.core.data_base

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.task.features.data.local.entities.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: UserEntity)

    @Query("SELECT * FROM user_table ")
    fun getAllData(): LiveData<List<UserEntity>>

}