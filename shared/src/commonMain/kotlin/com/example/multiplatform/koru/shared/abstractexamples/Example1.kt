package com.example.multiplatform.koru.shared.abstractexamples

import com.example.multiplatform.koru.shared.users.User
import com.example.multiplatform.koru.shared.users.UserService
import kotlinx.coroutines.flow.Flow
import com.futuremind.koru.ToNativeClass
import com.futuremind.koru.ToNativeInterface


@ToNativeClass
@ToNativeInterface
class Example1(private val service: UserService) {

    fun observeUsers(usersCount: Int): Flow<User> = service.loadRandomUsers(usersCount)

    suspend fun loadUser(username: String): User? = service.loadUser(username)

    fun getUserBlocking(username: String): User? = service.getUser(username)

}