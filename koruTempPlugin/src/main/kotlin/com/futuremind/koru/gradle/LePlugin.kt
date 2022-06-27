package com.futuremind.koru.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension


class LePlugin : Plugin<Project> {

    override fun apply(project: Project) {

        project.pluginManager.apply("com.google.devtools.ksp")

        project.tasks.matching { it.name.startsWith("compileKotlinIos") }
            .configureEach {
                println("depends ${this.name}")
                dependsOn("kspCommonMainKotlinMetadata")
            }

        project.dependencies {
            add("kspCommonMainMetadata", "com.futuremind:koru-processor:0.11.0")
        }


        project.extensions.getByType<KotlinMultiplatformExtension>()
            .sourceSets
            .matching { it.name.contains("ios") }
            .configureEach {
                println("B: ${this.name}")
                kotlin.srcDir("${project.buildDir.absolutePath}/generated/ksp/metadata/commonMain/kotlin")
            }

    }

}


