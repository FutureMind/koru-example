package com.example.multiplatform.koru.shared.abstractexamples

import com.example.multiplatform.koru.shared.users.User
import com.example.multiplatform.koru.shared.users.UserService
import kotlinx.coroutines.flow.Flow
import com.futuremind.koru.ToNativeClass
import com.futuremind.koru.ToNativeInterface


@ToNativeInterface
interface IExample2 {

    fun observeUsers(usersCount: Int): Flow<User>

    suspend fun loadUser(username: String): User?

    fun getUserBlocking(username: String): User?
}

@ToNativeClass
class Example2(private val service: UserService) : IExample2 {

    override fun observeUsers(usersCount: Int): Flow<User> = service.loadRandomUsers(usersCount)

    override suspend fun loadUser(username: String): User? = service.loadUser(username)

    override fun getUserBlocking(username: String): User? = service.getUser(username)

}