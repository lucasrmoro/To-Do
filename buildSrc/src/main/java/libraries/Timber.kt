package libraries

object Timber: DependencyContainer {

    private const val timber_version = "5.0.1"

    private const val timber = "com.jakewharton.timber:timber:$timber_version"

    override val all: List<String> = listOf(timber)

}