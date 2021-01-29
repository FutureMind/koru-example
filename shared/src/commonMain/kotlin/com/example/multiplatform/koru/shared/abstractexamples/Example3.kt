package com.example.multiplatform.koru.shared.abstractexamples

import com.example.multiplatform.koru.shared.users.User
import com.futuremind.koru.ToNativeClass
import kotlinx.coroutines.flow.Flow


@ToNativeClass
interface Example3 {

    fun observeUsers(usersCount: Int): Flow<User>

    suspend fun loadUser(username: String): User

    fun getUserBlocking(username: String): User
}