package extensions

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.`implementationAll`(dependencyList: List<Any>): List<Dependency?> =
    dependencyList.map { add("implementation", it) }

fun DependencyHandler.`apiAll`(dependencyList: List<Any>): List<Dependency?> =
    dependencyList.map { add("api", it) }

fun DependencyHandler.`testImplementationAll`(dependencyList: List<Any>): List<Dependency?> =
    dependencyList.map { add("testImplementation", it) }
