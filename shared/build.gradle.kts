import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.google.devtools.ksp") version "1.7.10-1.0.6"
    id("com.futuremind.koru").version("0.12.0")
}

kotlin {

    android()

    val xcf = XCFramework()

    ios {
        binaries.framework {
            baseName = "shared"
            xcf.add(this)
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = "shared"
            xcf.add(this)
        }
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")
                implementation("com.futuremind:koru:0.12.0")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
            }
        }

        val appleMain by creating {
            dependsOn(commonMain)
        }

        val iosArm64Main by sourceSets.getting { dependsOn(appleMain) }
        val iosSimulatorArm64Main by sourceSets.getting { dependsOn(appleMain) }
        val iosX64Main by sourceSets.getting { dependsOn(appleMain) }

        val commonTest by getting

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

koru {
    nativeSourceSetNames = listOf("appleMain")
}
