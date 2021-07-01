package com.example.multiplatform.koru.shared.abstractexamples

import com.example.multiplatform.koru.shared.users.MainScopeProvider
import com.futuremind.koru.ToNativeClass
import kotlinx.coroutines.*


/**
 * The wrapper will not be frozen, and therefore can be mutable.
 *
 * suspend / Flow work can still be done on background thread, the wrapper itself is
 * not safe to pass across threads, though.
 */
@ToNativeClass(
    name = "MutableClassExampleIos",
    freeze = false,
    launchOnScope = MainScopeProvider::class
)
class MutableClassExample {

    private var jobsFinished = 0
    private var youHadOneJob: Job? = null

    fun runUnlessItsAlreadyRunning() {
        if (youHadOneJob?.isActive == true) return
        youHadOneJob = MainScope().launch {
            delay(5000)
            jobsFinished ++
        }
        println("Finished job $jobsFinished")
    }

}

/**
 * The wrapper will be frozen, therefore it can be safely passed across threads.
 */
@ToNativeClass(
    name = "FrozenClassExampleIos",
    freeze = true,
    launchOnScope = MainScopeProvider::class
)
class FrozenClassExample {

    suspend fun whatever() = "whatever"

}

