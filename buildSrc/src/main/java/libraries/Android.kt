package libraries

object Android : DependencyContainer {

    private const val app_compat_version = "1.2.0"
    private const val core_version = "1.8.0"
    private const val constraint_version = "2.1.4"
    private const val navigation_version = "2.5.2"
    private const val lifecycle_version = "2.2.0"
    private const val room_version = "2.4.3"

    private const val appcompat = "androidx.appcompat:appcompat:$app_compat_version"
    private const val core = "androidx.core:core-ktx:$core_version"
    private const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraint_version"
    private const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigation_version"
    private const val navigationUi = "androidx.navigation:navigation-fragment-ktx:$navigation_version"
    private const val lifeCycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
    private const val lifeCycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    private const val roomRuntime = "androidx.room:room-runtime:$room_version"
    private const val roomCompiler = "androidx.room:room-compiler:$room_version"
    private const val roomKtx = "androidx.room:room-ktx:$room_version"

    override val all: List<String> = listOf(
        appcompat,
        core,
        constraintLayout,
        navigationFragment,
        navigationUi,
        lifeCycleLiveData,
        lifeCycleViewModel,
        roomRuntime,
        roomKtx
    )

    override val kapt = listOf(roomCompiler)
}