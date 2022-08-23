pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
//        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
        mavenLocal()
    }
    
}
rootProject.name = "suspend-wrapper-example"


include("androidApp")
include("shared")

