import modules.BuildModules.CORE
import modules.BuildModules.UIKIT
import modules.BuildModules.TASKS

plugins {
    id("com.android.application")
    id("android-application-config-plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("kotlin-parcelize")
}

android {
    dynamicFeatures.addAll(listOf(TASKS))
}

dependencies {
    api(project(CORE))
    implementation(project(UIKIT))
}