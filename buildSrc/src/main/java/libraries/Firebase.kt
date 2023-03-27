package libraries

object Firebase: DependencyContainer {

    private const val bom_version = "28.4.2"

    const val bom = "com.google.firebase:firebase-bom:$bom_version"

    private const val fbAnalytics = "com.google.firebase:firebase-analytics-ktx"

    override val all: List<String> = listOf(
        fbAnalytics
    )

}