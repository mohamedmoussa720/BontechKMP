plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("co.touchlab.skie")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "BontechKMP"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Add common dependencies here
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                // Add Android-specific dependencies here
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    namespace = "com.bontech.kmp"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

tasks.register("assembleXCFramework") {
    dependsOn(
        "linkReleaseFrameworkIosArm64",
        "linkReleaseFrameworkIosX64",
        "linkReleaseFrameworkIosSimulatorArm64"
    )
    
    doLast {
        val buildDir = layout.buildDirectory.get().asFile
        val xcframeworkPath = "$buildDir/XCFrameworks/release/BontechKMP.xcframework"
        val fatFrameworkDir = "$buildDir/XCFrameworks/release/simulator"
        val fatFrameworkPath = "$fatFrameworkDir/BontechKMP.framework"
        
        // Delete old files
        delete(xcframeworkPath)
        delete(fatFrameworkDir)
        
        // Create directory for fat framework
        mkdir(fatFrameworkDir)
        
        // Copy framework structure from simulator arm64
        copy {
            from("$buildDir/bin/iosSimulatorArm64/releaseFramework/BontechKMP.framework")
            into(fatFrameworkPath)
        }
        
        // Create fat binary for simulators (x64 + arm64)
        exec {
            commandLine(
                "lipo",
                "-create",
                "$buildDir/bin/iosX64/releaseFramework/BontechKMP.framework/BontechKMP",
                "$buildDir/bin/iosSimulatorArm64/releaseFramework/BontechKMP.framework/BontechKMP",
                "-output",
                "$fatFrameworkPath/BontechKMP"
            )
        }
        
        // Create XCFramework
        exec {
            commandLine(
                "xcodebuild",
                "-create-xcframework",
                "-framework", "$buildDir/bin/iosArm64/releaseFramework/BontechKMP.framework",
                "-framework", fatFrameworkPath,
                "-output", xcframeworkPath
            )
        }
        
        println("✅ XCFramework created at: $xcframeworkPath")
    }
}

