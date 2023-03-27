import plugins.ConstPlugins.GradleUrls.JITPACK

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        mavenCentral()
        google()
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        maven(url = JITPACK)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}