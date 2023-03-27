import extensions.implementationAll
import modules.BuildModules.UIKIT

plugins {
    id("com.android.library")
    id("android-config-plugin")
    id("kotlin-kapt")
    id("com.gaelmarhic.quadrant")
}

quadrantConfig.generateByDefault = false

dependencies {
    implementation(project(UIKIT))
    implementation(platform(libraries.Firebase.bom))
    implementationAll(libraries.Firebase.all)
}