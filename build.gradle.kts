plugins {
    kotlin("multiplatform") version "1.9.20" apply false
    id("com.android.library") version "8.1.4" apply false
    id("co.touchlab.skie") version "0.6.1" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

