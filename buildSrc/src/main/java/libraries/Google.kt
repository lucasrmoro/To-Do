package libraries

object Google : DependencyContainer {

    private const val material_version = "1.6.1"
    private const val gson_version = "2.10.1"

    private const val material = "com.google.android.material:material:$material_version"
    private const val gson = "com.google.code.gson:gson:$gson_version"

    override val all: List<String> = listOf(
        material,
        gson
    )
}