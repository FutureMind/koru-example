package com.example.multiplatform.koru.shared

import com.example.multiplatform.koru.shared.di.commonModule
import com.example.multiplatform.koru.shared.users.LoadUserUseCaseIos
import com.example.multiplatform.koru.shared.users.ObserveUsersUseCaseIos
import com.example.multiplatform.koru.shared.users.SaveUserUseCaseIos
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.dsl.module


fun initIosDependencies() = startKoin {
    modules(commonModule, iosModule)
}

private val iosModule = module {
    factory { LoadUserUseCaseIos(get()) }
    factory { SaveUserUseCaseIos(get()) }
    factory { ObserveUsersUseCaseIos(get()) }
}

/**
 * This is a DI Component exposed for our Swift code. It contains all the business classes
 * that matter for the iOS app.
 */
class IosComponent : KoinComponent {
    fun provideLoadUserUseCase(): LoadUserUseCaseIos = get()
    fun provideSaveUserUseCase(): SaveUserUseCaseIos = get()
    fun provideObserveUserUseCase(): ObserveUsersUseCaseIos = get()
}