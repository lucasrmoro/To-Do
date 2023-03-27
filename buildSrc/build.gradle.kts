object InternalDep {
    const val android_build_tools = "com.android.tools.build:gradle:7.4.2"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10"
    const val quadrant_gradle_plugin = "gradle.plugin.com.gaelmarhic:quadrant:1.5"
    const val google_services_plugin = "com.google.gms:google-services:4.3.10"
    const val firebase_crashlytics_plugin = "com.google.firebase:firebase-crashlytics-gradle:2.7.1"
    const val firebase_performance_plugin = "com.google.firebase:perf-plugin:1.4.0"
    const val hilt_plugin = "com.google.dagger:hilt-android-gradle-plugin:2.45"
}

gradlePlugin {
    plugins {
        register("android-config-plugin") {
            id = "android-config-plugin"
            implementationClass = "plugins.AndroidConfigPlugin"
        }
        register("android-application-config-plugin") {
            id = "android-application-config-plugin"
            implementationClass = "plugins.AndroidApplicationConfigPlugin"
        }
    }
}

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    google()
    mavenCentral()
    maven(url = "https://plugins.gradle.org/m2/")
}

dependencies {
    compileOnly(gradleApi())

    implementation(InternalDep.android_build_tools)
    implementation(InternalDep.kotlin_gradle_plugin)
    implementation(InternalDep.quadrant_gradle_plugin)
    implementation(InternalDep.google_services_plugin)
    implementation(InternalDep.firebase_crashlytics_plugin)
    implementation(InternalDep.firebase_performance_plugin)
    implementation(InternalDep.hilt_plugin)
}