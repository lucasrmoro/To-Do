import extensions.implementationAll
import modules.BuildModules.CORE
import modules.BuildModules.UIKIT
import modules.BuildModules.TASKS
import org.jetbrains.kotlin.base.kapt3.collectAggregatedTypes

plugins {
    id("com.android.application")
    id("android-application-config-plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    dynamicFeatures.addAll(listOf(TASKS))
}

dependencies {
    api(project(CORE))
    implementationAll(libraries.DI.all)
    implementation(project(UIKIT))
}