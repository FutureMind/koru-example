package com.example.multiplatform.koru.shared.scope

import com.futuremind.koru.ExportedScopeProvider
import com.futuremind.koru.ScopeProvider
import kotlinx.coroutines.MainScope

@ExportedScopeProvider
class MainScopeProvider : ScopeProvider {
    override val scope = MainScope()
}