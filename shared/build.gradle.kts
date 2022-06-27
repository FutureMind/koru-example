import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.futuremind.koru.compiler-plugin")
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
                implementation("com.futuremind:koru:$koruVersion")
            }
        }

        val androidMain by getting

        val appleMain by creating {
            dependsOn(commonMain)
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

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 31
    }
}
