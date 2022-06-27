import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("com.android.library")
//    kotlin("kapt")
    id("com.google.devtools.ksp").version("1.6.21-1.0.6")
}

val koruVersion = "0.11.0"

kotlin {

    android()

    val xcf = XCFramework()

    //app store
    iosArm64 {
        binaries.framework {
            baseName = "shared"
            embedBitcode("bitcode")
            xcf.add(this)
        }
    }

    //ios simulator m1
    iosSimulatorArm64 {
        binaries.framework {
            baseName = "shared"
            xcf.add(this)
        }
    }

    //ios simulator intel
    iosX64 {
        binaries.framework {
            baseName = "shared"
            xcf.add(this)
        }
    }

    sourceSets {

        val coroutineVersion = "1.5.2-native-mt"

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion") {
                    version { strictly(coroutineVersion) }
                }
//                implementation("com.squareup.moshi:moshi:1.13.0")
                implementation("com.futuremind:koru:$koruVersion")
//                configurations.get("ksp").dependencies.add(
//                    org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency(
//                        "com.futuremind", "koru-processor", koruVersion
//                    )
//                )
            }
        }

        val androidMain by getting

        val appleMain by creating {
            dependsOn(commonMain)

//            kotlin.srcDir("${buildDir.absolutePath}/generated/source/kaptKotlin/")
            kotlin.srcDir("${buildDir.absolutePath}/generated/ksp/metadata/commonMain/kotlin")
        }

        val iosArm64Main by sourceSets.getting { dependsOn(appleMain) }
        val iosSimulatorArm64Main by sourceSets.getting { dependsOn(appleMain) }
        val iosX64Main by sourceSets.getting { dependsOn(appleMain) }

        val commonTest by getting {
            dependencies {
                implementation("io.mockative:mockative:1.2.3")
            }
        }

    }
}

@OptIn(ExperimentalStdlibApi::class)
dependencies {
    tasks
        .matching { it.name.startsWith("compileKotlinIos") }
        .configureEach {
            println("depends ${this.name}")
            dependsOn("kspCommonMainKotlinMetadata")
        }
    configurations
        .matching { it.name.startsWith("ksp") }
        .configureEach {
            println("AAA ${this.name}")
        }
    add("kspCommonMainMetadata", "com.futuremind:koru-processor:$koruVersion")
//    add("kspAndroid", "com.futuremind:koru-processor:$koruVersion")
//    add("kspIosX64", "com.futuremind:koru-processor:$koruVersion")
//    add("kspIosArm64", "com.futuremind:koru-processor:$koruVersion")
//    add("kspIosSimulatorArm64", "com.futuremind:koru-processor:$koruVersion")

//    add("kspAndroid", "com.futuremind:koru-processor:$koruVersion")
//    configurations
//        .filter { it.name.startsWith("ksp") && it.name.lowercase().contains("ios") }
//        .forEach {
//            println("aaa: ${it.name}")
//            add(it.name, "com.futuremind:koru-processor:$koruVersion")
//        }
//    configurations
//        .filter { it.name.startsWith("ksp") && it.name.contains("Test") }
//        .forEach {
//            add(it.name, "io.mockative:mockative-processor:1.2.3")
//        }
//    configurations
//        .filter { it.name.startsWith("ksp") }
//        .forEach {
//            add(it.name, "com.squareup.moshi:moshi-kotlin-codegen:1.13.0")
//        }
//    ksp("com.futuremind:koru-processor:$koruVersion")
//    add("kspIosX64", "com.futuremind:koru-processor:$koruVersion")
//    add("kspJvm", "com.futuremind:koru-processor:$koruVersion")
}

//afterEvaluate {
//    dependencies {
//        add("kspCommonMainMetadata", "com.futuremind:koru-processor:$koruVersion")
//    }
//}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 31
    }
}
