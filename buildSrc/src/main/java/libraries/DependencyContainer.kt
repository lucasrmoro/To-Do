package libraries

interface DependencyContainer {
    val all: List<String>
    val kapt: List<String>
        get() = emptyList()
}