# Quick Start Guide

Get started with BontechKMP in 5 minutes!

## 🚀 For iOS Developers

### 1. Build the Framework

```bash
./build-xcframework.sh
```

### 2. Push to GitHub

```bash
# First time setup
git remote add origin https://github.com/YOUR_USERNAME/BontechKMP.git
git branch -M main
git push -u origin main

# Create a release
git tag 1.0.0
git push origin 1.0.0
```

### 3. Add to Your iOS App

In Xcode:
- **File** → **Add Package Dependencies**
- Enter: `https://github.com/YOUR_USERNAME/BontechKMP`
- Select version **1.0.0**

### 4. Use It!

```swift
import BontechKMP

let greeting = Greeting()
print(greeting.greet())
```

## 📱 For Android Developers

### 1. Include the Module

In your app's `build.gradle.kts`:

```kotlin
dependencies {
    implementation(project(":shared"))
}
```

### 2. Use It!

```kotlin
import com.bontech.kmp.Greeting

val greeting = Greeting()
println(greeting.greet())
```

## 🛠 Development Workflow

### Making Changes

1. **Edit code** in `shared/src/commonMain/`
2. **Add platform-specific code** if needed:
   - Android: `shared/src/androidMain/`
   - iOS: `shared/src/iosMain/`
3. **Test locally**:
   ```bash
   ./gradlew build
   ```

### Publishing Updates

```bash
# Build for iOS
./build-xcframework.sh

# Commit and tag
git add .
git commit -m "Your changes"
git push

# Create new version
git tag 1.0.1
git push origin 1.0.1
```

## 📚 Next Steps

- Read the full [README.md](README.md) for detailed documentation
- Check [PUBLISHING_GUIDE.md](PUBLISHING_GUIDE.md) for publishing details
- Explore the code in `shared/src/` to understand the structure

## ❓ Common Issues

**"Framework not found"**
- Run `./build-xcframework.sh` first
- Ensure the `.xcframework` is committed to git

**"No such module 'BontechKMP'"**
- Check that the package is added in Xcode
- Try: File → Packages → Reset Package Caches

**Gradle build fails**
- Ensure JDK 11+ is installed: `java -version`
- Try: `./gradlew clean build`

## 💡 Tips

- Always run `./build-xcframework.sh` before creating a new release
- Use semantic versioning: `1.0.0` → `1.0.1` → `1.1.0` → `2.0.0`
- Test on both platforms before publishing
- Keep the XCFramework in git for Swift Package Manager

Happy coding! 🎉

