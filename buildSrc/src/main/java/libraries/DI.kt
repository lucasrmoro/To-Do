package libraries

object DI : DependencyContainer {

    private const val koin_version = "2.2.3"

    const val android = "io.insert-koin:koin-android:$koin_version"
    const val viewModel = "io.insert-koin:koin-android-viewmodel:$koin_version"

    override val all: List<String> = listOf(android, viewModel)

}