package libraries

object DI : DependencyContainer {

    private const val hilt_version = "2.45"

    private const val hilt = "com.google.dagger:hilt-android:$hilt_version"
    private const val hiltCompiler = "com.google.dagger:hilt-compiler:$hilt_version"

    override val all: List<String> = listOf(hilt)

    override val kapt = listOf(hiltCompiler)

}