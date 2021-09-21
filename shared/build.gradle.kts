import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("kapt")
}

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
        val koruVersion = "0.9.0"

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion") {
                    version { strictly(coroutineVersion) }
                }

                implementation("com.futuremind:koru:$koruVersion")
                configurations.get("kapt").dependencies.add(
                    org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency(
                        "com.futuremind", "koru-processor", koruVersion
                    )
                )
            }
        }

        val androidMain by getting

        val appleMain by creating {
            dependsOn(commonMain)
            kotlin.srcDir("${buildDir.absolutePath}/generated/source/kaptKotlin/")
        }

        val iosArm64Main by sourceSets.getting { dependsOn(appleMain) }
        val iosSimulatorArm64Main by sourceSets.getting { dependsOn(appleMain) }
        val iosX64Main by sourceSets.getting { dependsOn(appleMain) }

    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 31
    }
}
