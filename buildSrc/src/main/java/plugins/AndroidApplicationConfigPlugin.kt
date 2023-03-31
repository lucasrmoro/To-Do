package plugins

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import libraries.*
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import plugins.ConstPlugins.APPLICATION_ID
import plugins.ConstPlugins.Implementations.ANDROID_TEST_IMPLEMENTATION
import plugins.ConstPlugins.Implementations.IMPLEMENTATION
import plugins.ConstPlugins.Implementations.TEST_IMPLEMENTATION
import plugins.ConstPlugins.Implementations.KAPT
import plugins.ConstPlugins.BUILD_TYPE_DEBUG
import plugins.ConstPlugins.Proguard.ANDROID_OPTIMIZE
import plugins.ConstPlugins.Proguard.RULES_PRO
import plugins.ConstPlugins.BUILD_TYPE_RELEASE

class AndroidApplicationConfigPlugin : BaseAndroidConfigPlugin() {

    override fun generalSettingsAndroid(extension: BaseExtension) {
        when (extension) {
            is AppExtension -> {
                extension.run {
                    applyBuildTypesSettings()
                    applyFlavorsSettings()
                }
            }
        }
    }

    override fun applyDependencies(project: Project) {
        project.applyDependenciesSettings()
    }

    private fun BaseExtension.applyBuildTypesSettings() {
        buildTypes {
            getByName(BUILD_TYPE_DEBUG) {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile(ANDROID_OPTIMIZE), RULES_PRO)
            }

            getByName(BUILD_TYPE_RELEASE) {
                isMinifyEnabled = true
                proguardFiles(getDefaultProguardFile(ANDROID_OPTIMIZE), RULES_PRO)
            }
        }
    }

    private fun BaseExtension.applyFlavorsSettings() {
        defaultConfig {
            applicationId = APPLICATION_ID
            multiDexEnabled = true
        }
    }

    private fun Project.applyDependenciesSettings() {
        this.dependencies {
            apply(plugin = "kotlin-kapt")
            Google.all.forEach { add(IMPLEMENTATION, it) }
            Android.all.forEach { add(IMPLEMENTATION, it) }
            Android.kapt.forEach { add(KAPT, it) }
            Kotlin.all.forEach { add(IMPLEMENTATION, it) }
            DI.all.forEach { add(IMPLEMENTATION, it) }
            Timber.all.forEach { add(IMPLEMENTATION, it) }
            Tests.run {
                allUnit.forEach { add(TEST_IMPLEMENTATION, it) }
                allInstrumented.forEach { add(ANDROID_TEST_IMPLEMENTATION, it) }
            }
        }
    }
}
