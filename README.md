# BontechKMP

A Kotlin Multiplatform (KMP) library that can be used in both Android and iOS applications. This library is distributed as a Swift Package for easy integration into iOS projects.

## Features

- ✅ Kotlin Multiplatform support (Android & iOS)
- ✅ Swift Package Manager integration
- ✅ Pre-built XCFramework for iOS
- ✅ Simple platform abstraction layer

## Project Structure

```
BontechKMP/
├── shared/                          # Shared KMP module
│   ├── src/
│   │   ├── commonMain/             # Platform-independent code
│   │   ├── androidMain/            # Android-specific code
│   │   └── iosMain/                # iOS-specific code
│   └── build.gradle.kts
├── Package.swift                    # Swift Package Manager manifest
├── build-xcframework.sh            # Script to build iOS XCFramework
└── README.md
```

## Setup & Build

### Prerequisites

- JDK 11 or higher
- Xcode 14+ (for iOS development)
- Android Studio (for Android development)

### Building the XCFramework

To build the iOS XCFramework for distribution:

```bash
./build-xcframework.sh
```

This will:
1. Build the framework for all iOS architectures (x64, arm64, simulator arm64)
2. Create an XCFramework at `./BontechKMP.xcframework`

## Using in iOS Projects

### Via Swift Package Manager

1. **Prepare the Package:**
   - Build the XCFramework using `./build-xcframework.sh`
   - Commit all changes including the `.xcframework` directory
   - Create a git tag: `git tag 1.0.0`
   - Push to GitHub: `git push origin main --tags`

2. **Add to Your iOS Project:**
   - Open your iOS project in Xcode
   - Go to File → Add Package Dependencies
   - Enter your GitHub repository URL
   - Select the version/tag you want to use

3. **Use in Your Code:**

```swift
import BontechKMP

let greeting = Greeting()
print(greeting.greet())  // Output: "Hello, iOS [version]!"
```

## Using in Android Projects

Add the shared module as a dependency in your Android app's `build.gradle.kts`:

```kotlin
dependencies {
    implementation(project(":shared"))
}
```

Use in your Kotlin code:

```kotlin
import com.bontech.kmp.Greeting

val greeting = Greeting()
println(greeting.greet())  // Output: "Hello, Android [SDK version]!"
```

## Development

### Building Locally

```bash
# Build the entire project
./gradlew build

# Build only the shared module
./gradlew :shared:build

# Run tests
./gradlew test
```

### Adding New Features

1. Add common code in `shared/src/commonMain/kotlin/com/bontech/kmp/`
2. Add platform-specific implementations in `androidMain` and `iosMain`
3. Build and test on both platforms
4. Update the XCFramework for iOS distribution

## API Reference

### Greeting

A simple greeting class that demonstrates platform-specific implementations.

```kotlin
class Greeting {
    fun greet(): String
}
```

Returns a greeting message with the current platform name.

### Platform

An interface for accessing platform-specific information.

```kotlin
interface Platform {
    val name: String
}
```

## Publishing Updates

1. Make your changes to the shared code
2. Update version numbers if needed
3. Build the XCFramework: `./build-xcframework.sh`
4. Commit all changes including the `.xcframework`
5. Create a new tag: `git tag 1.0.1`
6. Push: `git push origin main --tags`

iOS projects using the package will be able to update to the new version.

## License

[Add your license here]

## Contributing

[Add contribution guidelines here]

## Support

For issues and questions, please use the GitHub issue tracker.

