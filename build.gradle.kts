import plugins.ConstPlugins.GradleUrls.JITPACK

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.21")
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        maven(url = JITPACK)
        maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}