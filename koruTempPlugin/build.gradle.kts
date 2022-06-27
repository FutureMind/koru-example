plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("gradle-plugin"))
    implementation("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.6.21-1.0.6")
}

gradlePlugin {
    plugins {
        create("compilerPlugin") {
            id = "com.futuremind.koru"
            implementationClass = "com.futuremind.koru.gradle.CompilerPlugin"
        }
    }
}
