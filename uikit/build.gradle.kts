import extensions.implementationAll
import libraries.Android

plugins {
    id("com.android.library")
    id("android-config-plugin")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

dependencies {
    implementationAll(Android.all)
}