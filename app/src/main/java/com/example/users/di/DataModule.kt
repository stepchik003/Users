package com.example.users.di

import android.content.Context
import com.example.users.data.local.UserDao
import com.example.users.data.local.UserDatabase
import com.example.users.data.mapper.UserMapper
import com.example.users.data.remote.UserApi
import com.example.users.data.repository.UserRepositoryImpl
import com.example.users.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideUserApi(): UserApi {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return UserDatabase.getDatabase(context)
    }

    @Provides
    fun provideUserDao(database: UserDatabase): UserDao = database.userDao()

    @Provides
    fun provideUserMapper(): UserMapper = UserMapper()

    @Provides
    @Singleton
    fun provideUserRepository(
        userApi: UserApi,
        userDao: UserDao,
        userMapper: UserMapper
    ): UserRepository {
        return UserRepositoryImpl(userApi, userDao, userMapper)
    }
}