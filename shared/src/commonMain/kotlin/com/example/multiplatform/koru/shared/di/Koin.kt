package com.example.multiplatform.koru.shared.di

import com.example.multiplatform.koru.shared.users.LoadUserUseCase
import com.example.multiplatform.koru.shared.users.ObserveUsersUseCase
import com.example.multiplatform.koru.shared.users.SaveUserUseCase
import com.example.multiplatform.koru.shared.users.UserService
import org.koin.dsl.module

val commonModule = module {
    single { UserService() }
    factory { LoadUserUseCase(get()) }
    factory { SaveUserUseCase(get()) }
    factory { ObserveUsersUseCase(get()) }
}