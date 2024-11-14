package com.project.task.core.di

import android.content.Context
import androidx.room.Room
import com.project.task.core.data_base.MyDatabase
import com.project.task.core.data_base.UserDao
import com.project.task.features.data.repository.UserDataRepositoryImpl
import com.project.task.features.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): MyDatabase {
        return Room.databaseBuilder(context, MyDatabase::class.java, MyDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideUserDao(myDatabase: MyDatabase): UserDao {
        return myDatabase.userDao()
    }
    @Provides
    fun provideDataRepository(dao: UserDao): UserRepository = UserDataRepositoryImpl(dao)

}
