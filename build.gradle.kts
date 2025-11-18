plugins {
    kotlin("multiplatform") version "1.9.20" apply false
    id("com.android.library") version "8.1.4" apply false
    id("co.touchlab.skie") version "0.6.1" apply false
}

// Kotlin version for KMP - update this when upgrading Kotlin
val kotlinVersion = "1.9.20"
extra["kotlinVersion"] = kotlinVersion

// Library version - update this when releasing a new version of your library
val libraryVersion = "1.0.3"
extra["libraryVersion"] = libraryVersion

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

