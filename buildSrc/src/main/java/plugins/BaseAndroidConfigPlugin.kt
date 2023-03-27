package plugins

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import plugins.ConstPlugins.ANDROID_JUNIT_RUNNER
import plugins.ConstPlugins.BUILD_TOOLS_VERSION
import plugins.ConstPlugins.COMPILE_SDK_VERSION
import plugins.ConstPlugins.EXTENSION_ANDROID
import plugins.ConstPlugins.JVM_TARGET
import plugins.ConstPlugins.MIN_SDK_VERSION
import plugins.ConstPlugins.PLUGIN_KOTLIN_ANDROID
import plugins.ConstPlugins.Proguard.RULES_PRO
import plugins.ConstPlugins.VERSION_CODE
import plugins.ConstPlugins.VERSION_NAME

abstract class BaseAndroidConfigPlugin : Plugin<Project> {

    lateinit var rootPathProject: String
    abstract fun generalSettingsAndroid(extension: BaseExtension)
    abstract fun applyDependencies(project: Project)

    override fun apply(project: Project) {
        project.run {
            rootPathProject = this.projectDir.parent
            plugins.apply(PLUGIN_KOTLIN_ANDROID)
            val androidExtension = project.extensions.getByName(EXTENSION_ANDROID)
            if (androidExtension is BaseExtension) {
                androidExtension.also {
                    it.applyAndroidSettings()
                    it.enableJava8(project)
                    generalSettingsAndroid(it)
                }
            }
            applyDependencies(this)
        }
    }

    private fun BaseExtension.applyAndroidSettings() {
        compileSdkVersion(COMPILE_SDK_VERSION)
        buildToolsVersion(BUILD_TOOLS_VERSION)
        defaultConfig {
            minSdkVersion(MIN_SDK_VERSION)
            targetSdkVersion(COMPILE_SDK_VERSION)
            versionCode = VERSION_CODE
            versionName = VERSION_NAME
            testInstrumentationRunner = ANDROID_JUNIT_RUNNER
            consumerProguardFile(RULES_PRO)
        }
        buildFeatures.viewBinding = true
    }

    private fun BaseExtension.enableJava8(project: Project) {
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        project.tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = JVM_TARGET
            }
        }
    }
}