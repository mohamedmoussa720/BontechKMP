package com.bontech.kmp

/**
 * Library version constant - accessible directly from Swift
 * This version is automatically generated from the Gradle build configuration
 */
val LIBRARY_VERSION = "1.0.0"

/**
 * Returns the Kotlin Multiplatform (KMP) version used in this library
 * This version is automatically generated from the Gradle build configuration
 * @return The KMP version string (e.g., "1.9.20")
 */
fun getKmpVersion(): String {
    return "1.9.20"
}

/**
 * Returns the Kotlin Multiplatform (KMP) version used in this library
 * Alias for [getKmpVersion] for convenience
 */
fun kmpVersion(): String = getKmpVersion()

/**
 * Returns the library version of BontechKMP
 * This version is automatically generated from the Gradle build configuration
 * Update the libraryVersion in build.gradle.kts to change this value
 * @return The library version string (e.g., "1.0.0")
 */
fun getLibraryVersion(): String {
    return "1.0.0"
}

/**
 * Returns the library version of BontechKMP
 * Alias for [getLibraryVersion] for convenience
 */
fun libraryVersion(): String = getLibraryVersion()

/**
 * Returns the library version of BontechKMP
 * Shorter alias for [getLibraryVersion]
 */
fun version(): String = getLibraryVersion()
