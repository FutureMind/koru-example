package com.example.multiplatform.koru.shared.users

import com.futuremind.koru.ToNativeClass
import kotlinx.coroutines.flow.Flow


//all in one file for presentation purpose

@ToNativeClass(name = "LoadUserUseCaseIos", launchOnScope = MainScopeProvider::class)
class LoadUserUseCase(private val service: UserService){

    suspend fun loadUser(username: String) : User? = service.loadUser(username)

    fun getUserBlocking(username: String) : User? = service.getUser(username)

}

@ToNativeClass(name = "ObserveUsersUseCaseIos", launchOnScope = MainScopeProvider::class)
class ObserveUsersUseCase(private val service: UserService){

    fun observeUsers(usersCount: Int) : Flow<User> = service.loadRandomUsers(usersCount)

}

@ToNativeClass(name = "SaveUserUseCaseIos", launchOnScope = MainScopeProvider::class)
class SaveUserUseCase(private val service: UserService){

    suspend fun saveUser(user: User) = service.sendUser(user)

}