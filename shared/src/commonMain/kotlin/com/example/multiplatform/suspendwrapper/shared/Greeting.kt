package com.example.multiplatform.suspendwrapper.shared

import com.futuremind.iossuspendwrapper.WrapForIos


@WrapForIos
class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
