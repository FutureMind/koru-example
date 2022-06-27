pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
//        mavenLocal()
    }
    
}
rootProject.name = "suspend-wrapper-example"


includeBuild("koruTempPlugin")
include("androidApp")
include("shared")

