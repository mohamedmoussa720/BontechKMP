#!/bin/bash

# Build the XCFramework for iOS distribution via Swift Package Manager
# This script builds the Kotlin Multiplatform framework for all iOS architectures
# and packages them into an XCFramework

set -e

echo "Building iOS frameworks..."

# Build the framework for all architectures
./gradlew :shared:assembleXCFramework

# Copy the XCFramework to the root directory for Swift Package Manager
echo "Copying XCFramework..."
rm -rf BontechKMP.xcframework
cp -R shared/build/XCFrameworks/release/BontechKMP.xcframework ./

echo "✅ XCFramework built successfully!"
echo "The framework is ready at: ./BontechKMP.xcframework"
echo ""
echo "To use this in your iOS project:"
echo "1. Commit and push the changes to GitHub"
echo "2. Create a release tag (e.g., git tag 1.0.0 && git push origin 1.0.0)"
echo "3. In Xcode, add the GitHub repository URL as a Swift Package"

