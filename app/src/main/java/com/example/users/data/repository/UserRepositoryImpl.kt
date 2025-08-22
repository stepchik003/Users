package com.example.users.data.repository

import com.example.users.data.local.UserDao
import com.example.users.data.mapper.UserMapper
import com.example.users.data.remote.UserApi
import com.example.users.domain.model.User
import com.example.users.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val userDao: UserDao,
    private val userMapper: UserMapper
) : UserRepository {

    override suspend fun getUsers(forceRefresh: Boolean): Result<List<User>> {
        return try {
            if (forceRefresh) {
                val users = userApi.getUsers(20).results
                userDao.insertAll(users.map { userMapper.toEntity(it) })
                Result.success(users)
            } else {
                val cachedUsers = userDao.getAll().map { userMapper.toUser(it) }
                if (cachedUsers.isEmpty()) {
                    getUsers(true)
                } else {
                    Result.success(cachedUsers)
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserById(id: String): Result<User> {
        return try {
            val userEntity = userDao.getById(id)
            if (userEntity != null) {
                Result.success(userMapper.toUser(userEntity))
            } else {
                Result.failure(Exception("User not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}