package com.example.multiplatform.koru.shared

import com.example.multiplatform.koru.shared.abstractexamples.FrozenClassExampleIos
import com.example.multiplatform.koru.shared.abstractexamples.MutableClassExampleIos
import com.example.multiplatform.koru.shared.di.CommonComponent
import com.example.multiplatform.koru.shared.users.LoadUserUseCaseIos
import com.example.multiplatform.koru.shared.users.ObserveUsersUseCaseIos
import com.example.multiplatform.koru.shared.users.SaveUserUseCaseIos
import com.example.multiplatform.koru.shared.viewmodel.CountdownViewModelIos


/**
 * This is a DI Component exposed for our Swift code. It contains all the business classes
 * that matter for the iOS app.
 */
class IosComponent {
    private val commonComponent = CommonComponent()
    fun provideLoadUserUseCase(): LoadUserUseCaseIos = LoadUserUseCaseIos(commonComponent.loadUserUseCase)
    fun provideSaveUserUseCase(): SaveUserUseCaseIos = SaveUserUseCaseIos(commonComponent.saveUserUseCase)
    fun provideObserveUserUseCase(): ObserveUsersUseCaseIos = ObserveUsersUseCaseIos(commonComponent.observeUserUseCase)
    fun provideMutableExample(): MutableClassExampleIos = MutableClassExampleIos(commonComponent.mutableClassExample)
    fun provideFrozenExample(): FrozenClassExampleIos = FrozenClassExampleIos(commonComponent.frozenClassExample)
    fun provideCountdownViewModel(): CountdownViewModelIos = CountdownViewModelIos(commonComponent.countdownViewModel)
}