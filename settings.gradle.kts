pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    
}
rootProject.name = "suspend-wrapper-example"


includeBuild("koruTempPlugin")
include("androidApp")
include("shared")

