import extensions.implementationAll
import modules.BuildModules.UIKIT

plugins {
    id("kotlin-kapt")
    id("com.android.library")
    id("android-config-plugin")
    id("com.gaelmarhic.quadrant")
}

quadrantConfig.generateByDefault = false

dependencies {
    implementation(project(UIKIT))
    implementation(platform(libraries.Firebase.bom))
    implementationAll(libraries.Firebase.all)
}