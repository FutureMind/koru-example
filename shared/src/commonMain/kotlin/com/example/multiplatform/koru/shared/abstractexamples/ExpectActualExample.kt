package com.example.multiplatform.koru.shared.abstractexamples

import com.futuremind.koru.ToNativeClass
import com.futuremind.koru.ToNativeInterface

@ToNativeInterface
expect abstract class ViewModel()

interface IrrelevantInterface

@ToNativeClass
class SomeSpecificViewModel : ViewModel(), IrrelevantInterface