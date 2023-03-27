package plugins

import com.android.build.gradle.BaseExtension
import libraries.*
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.plugins
import plugins.ConstPlugins.Implementations.ANDROID_TEST_IMPLEMENTATION
import plugins.ConstPlugins.Implementations.IMPLEMENTATION
import plugins.ConstPlugins.Implementations.KAPT
import plugins.ConstPlugins.Implementations.TEST_IMPLEMENTATION

class AndroidConfigPlugin : BaseAndroidConfigPlugin() {

    override fun generalSettingsAndroid(extension: BaseExtension) {}

    override fun applyDependencies(project: Project) {
        project.applyDependenciesSettings()
    }

    private fun Project.applyDependenciesSettings() {
        this.dependencies {
            apply(plugin = "dagger.hilt.android.plugin")
            apply(plugin = "kotlin-kapt")
            DI.all.forEach { add(IMPLEMENTATION, it) }
            DI.kapt.forEach { add(KAPT, it) }
            Google.all.forEach { add(IMPLEMENTATION, it) }
            Android.all.forEach { add(IMPLEMENTATION, it) }
            Android.kapt.forEach { add(KAPT, it) }
            Kotlin.all.forEach { add(IMPLEMENTATION, it) }
            Timber.all.forEach { add(IMPLEMENTATION, it) }
            Tests.run {
                allUnit.forEach { add(TEST_IMPLEMENTATION, it) }
                allInstrumented.forEach { add(ANDROID_TEST_IMPLEMENTATION, it) }
            }
        }
    }
}
