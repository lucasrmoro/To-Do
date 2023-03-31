package plugins

object ConstPlugins {

    const val PLUGIN_KOTLIN_ANDROID = "kotlin-android"
    const val EXTENSION_ANDROID = "android"
    const val ANDROID_JUNIT_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    const val BUILD_TYPE_DEBUG = "debug"
    const val BUILD_TYPE_RELEASE = "release"
    const val JVM_TARGET = "1.8"
    const val BUILD_TOOLS_VERSION = "30.0.3"
    const val COMPILE_SDK_VERSION = 33
    const val MIN_SDK_VERSION = 21
    const val VERSION_CODE = 1
    const val APPLICATION_ID = "br.com.lucas.todo"
    const val VERSION_NAME = "1.0.0"

    object Implementations {
        const val KAPT = "kapt"
        const val IMPLEMENTATION = "implementation"
        const val TEST_IMPLEMENTATION = "testImplementation"
        const val ANDROID_TEST_IMPLEMENTATION = "androidTestImplementation"
    }

    object Proguard {
        const val ANDROID_OPTIMIZE = "proguard-android-optimize.txt"
        const val RULES_PRO = "proguard-rules.pro"
    }

    object GradleUrls {
        const val JITPACK = "https://jitpack.io"
    }
}