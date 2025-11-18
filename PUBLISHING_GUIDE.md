# Publishing Guide for BontechKMP

This guide will help you publish your KMP library to GitHub and make it available as a Swift Package for iOS apps.

## Step 1: Build the XCFramework

Before publishing, you need to build the XCFramework for iOS:

```bash
./build-xcframework.sh
```

This creates `BontechKMP.xcframework` in the root directory. The Package.swift file references this framework.

## Step 2: Initialize Git Repository (if not already done)

```bash
git init
git add .
git commit -m "Initial commit: BontechKMP library"
```

## Step 3: Create a GitHub Repository

1. Go to [GitHub](https://github.com/new)
2. Create a new repository named `BontechKMP` (or your preferred name)
3. **Do NOT** initialize it with README, .gitignore, or license (we already have these)

## Step 4: Push to GitHub

```bash
# Add the remote repository
git remote add origin https://github.com/YOUR_USERNAME/BontechKMP.git

# Push to GitHub
git branch -M main
git push -u origin main
```

## Step 5: Create a Release Tag

Tags are important for version management in Swift Package Manager:

```bash
# Create and push a tag
git tag 1.0.0
git push origin 1.0.0
```

## Step 6: Using in an iOS App

### Option A: Direct GitHub Integration

1. Open your iOS project in Xcode
2. Go to **File → Add Package Dependencies**
3. Enter your repository URL: `https://github.com/YOUR_USERNAME/BontechKMP`
4. Select the version (1.0.0)
5. Click "Add Package"

### Option B: Package.swift for SPM Projects

Add to your `Package.swift`:

```swift
dependencies: [
    .package(url: "https://github.com/YOUR_USERNAME/BontechKMP.git", from: "1.0.0")
]
```

## Updating the Library

When you make changes:

1. **Make your code changes** in the `shared` module
2. **Build the XCFramework**: `./build-xcframework.sh`
3. **Commit the changes**:
   ```bash
   git add .
   git commit -m "Description of changes"
   git push
   ```
4. **Create a new release tag**:
   ```bash
   git tag 1.0.1
   git push origin 1.0.1
   ```

iOS apps can now update to version 1.0.1.

## Important Notes

### XCFramework in Git

The `.xcframework` directory needs to be committed to your repository because Swift Package Manager downloads it directly from GitHub. This is different from CocoaPods which builds from source.

**Pros:**
- Faster integration (no build required)
- iOS developers don't need Kotlin/Gradle installed

**Cons:**
- Large binary files in git repository
- Repository size grows with each version

### Alternative: Git LFS

For larger frameworks, consider using Git Large File Storage:

```bash
# Install Git LFS
brew install git-lfs
git lfs install

# Track the XCFramework
git lfs track "*.xcframework/**"
git add .gitattributes
git commit -m "Add Git LFS tracking"
```

## Troubleshooting

### "Framework not found" Error

- Ensure you ran `./build-xcframework.sh` before committing
- Verify the `.xcframework` directory exists in your repository
- Check that the path in `Package.swift` matches the actual location

### Build Errors in Xcode

- Clean build folder: **Product → Clean Build Folder** (⇧⌘K)
- Reset package cache: **File → Packages → Reset Package Caches**
- Update packages: **File → Packages → Update to Latest Package Versions**

### Outdated Version in Xcode

- Update packages: **File → Packages → Update to Latest Package Versions**
- Or manually select a different version in package dependencies

## Versioning Strategy

Use [Semantic Versioning](https://semver.org/):

- **1.0.0**: Initial release
- **1.0.1**: Bug fixes (patch)
- **1.1.0**: New features, backward compatible (minor)
- **2.0.0**: Breaking changes (major)

## GitHub Release Notes

Consider creating GitHub releases with changelogs:

1. Go to your repository on GitHub
2. Click **Releases** → **Create a new release**
3. Select your tag (e.g., 1.0.0)
4. Add release notes describing changes
5. Publish release

This makes it easier for users to see what changed between versions.

