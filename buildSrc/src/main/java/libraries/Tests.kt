package libraries

object Tests: DependencyContainer {

    private const val truth_version = "1.1.3"
    private const val junit_version = "4.12"
    private const val espresso_version = "3.3.0"
    private const val mockk_version = "1.10.6"
    private const val kotlinx_coroutines_test_version = "1.6.0"
    private const val androidx_arch_core_test_version = "2.1.0"

    private const val truth = "com.google.truth:truth:$truth_version"
    private const val junit = "junit:junit:$junit_version"
    private const val espresso = "androidx.test.espresso:espresso-core:$espresso_version"
    private const val mockk = "io.mockk:mockk:$mockk_version"
    private const val kotlin_coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinx_coroutines_test_version"
    private const val androidx_arch_core_test = "androidx.arch.core:core-testing:$androidx_arch_core_test_version"

    override val all: List<String> = listOf(
        kotlin_coroutines_test,
        androidx_arch_core_test,
        junit,
        espresso,
        mockk
    )

    val allUnit: List<String> = listOf(
        truth,
        kotlin_coroutines_test,
        androidx_arch_core_test,
        junit,
        mockk
    )

    val allInstrumented: List<String> = listOf(
        espresso
    )

}