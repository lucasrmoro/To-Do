package libraries

object Kotlin: DependencyContainer {

    private const val kotlin_version = "1.6.0"

    private const val name = "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"

    override val all: List<String> = listOf(
        name
    )

}