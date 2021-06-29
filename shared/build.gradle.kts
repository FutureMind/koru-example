import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("kapt")
}

kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    sourceSets {

        val coroutineVersion = "1.5.0-native-mt"
        val koruVersion = "0.5.0"

        val commonMain by getting {
            dependencies {


                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion") {
                    version { strictly(coroutineVersion) }
                }
                implementation("io.insert-koin:koin-core:3.1.1")

                implementation("com.futuremind:koru:$koruVersion")
                configurations.get("kapt").dependencies.add(
                    org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency(
                        "com.futuremind", "koru-processor", koruVersion
                    )
                )
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.3.0")
            }
        }
        val iosMain by getting {
            kotlin.srcDir("${buildDir.absolutePath}/generated/source/kaptKotlin/")
        }
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(30)
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework =
        kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}

tasks.getByName("build").dependsOn(packForXcode)