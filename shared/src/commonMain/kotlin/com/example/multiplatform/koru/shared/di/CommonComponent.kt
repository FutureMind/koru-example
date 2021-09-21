package com.example.multiplatform.koru.shared.di

import com.example.multiplatform.koru.shared.abstractexamples.FrozenClassExample
import com.example.multiplatform.koru.shared.abstractexamples.MutableClassExample
import com.example.multiplatform.koru.shared.users.LoadUserUseCase
import com.example.multiplatform.koru.shared.users.ObserveUsersUseCase
import com.example.multiplatform.koru.shared.users.SaveUserUseCase
import com.example.multiplatform.koru.shared.users.UserService

class CommonComponent {
    private val userService = UserService()
    val loadUserUseCase = LoadUserUseCase(userService)
    val saveUserUseCase = SaveUserUseCase(userService)
    val observeUserUseCase = ObserveUsersUseCase(userService)
    val mutableClassExample = MutableClassExample()
    val frozenClassExample = FrozenClassExample()
}
