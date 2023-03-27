package libraries

object DI : DependencyContainer {

    private const val hilt_version = "2.44"

    private const val hilt = "com.google.dagger:hilt-android:$hilt_version"
    private const val hiltCompilerAndroidX = "androidx.hilt:hilt-compiler:1.0.0"
    private const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hilt_version"

    override val all: List<String> = listOf(hilt)

    override val kapt = listOf(hiltCompiler, hiltCompilerAndroidX)

}