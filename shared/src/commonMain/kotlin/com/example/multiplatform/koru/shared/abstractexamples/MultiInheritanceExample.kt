package com.example.multiplatform.koru.shared.abstractexamples

import com.futuremind.koru.ToNativeInterface

@ToNativeInterface
class W : X {
    override suspend fun x(whatever: Int) : Float = TODO()
    override suspend fun y(whatever: Int) : Float = TODO()
    override suspend fun z(whatever: Int) : Float = TODO()
    suspend fun w(whatever: Int) : Float = TODO()
}

@ToNativeInterface
interface X : Y, Z {
    suspend fun x(whatever: Int) : Float
    override suspend fun y(whatever: Int) : Float
    override suspend fun z(whatever: Int) : Float
}

@ToNativeInterface
interface Y : Z {
    suspend fun y(whatever: Int) : Float
    override suspend fun z(whatever: Int) : Float
}

@ToNativeInterface
interface Z {
    suspend fun z(whatever: Int) : Float
}