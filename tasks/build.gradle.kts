import modules.BuildModules.UIKIT
import modules.BuildModules.APP

plugins {
    id("com.android.dynamic-feature")
    id("android-config-plugin")
    id("kotlin-parcelize")
}

dependencies {
    implementation(project(APP))
    implementation(project(UIKIT))
}