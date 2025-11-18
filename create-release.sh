#!/bin/bash

# Script to create a GitHub release with XCFramework
# Usage: ./create-release.sh <version> [release-notes]
# Example: ./create-release.sh 1.0.0 "Initial release"

set -e

VERSION=$1
RELEASE_NOTES=${2:-"Release $VERSION"}

if [ -z "$VERSION" ]; then
    echo "❌ Error: Version is required"
    echo "Usage: ./create-release.sh <version> [release-notes]"
    echo "Example: ./create-release.sh 1.0.0 \"Initial release\""
    exit 1
fi

echo "🔨 Building XCFramework..."
./build-xcframework.sh

echo "📦 Creating zip archive..."
rm -f BontechKMP.xcframework.zip
zip -r BontechKMP.xcframework.zip BontechKMP.xcframework/ -x "*.DS_Store"
ZIP_SIZE=$(du -h BontechKMP.xcframework.zip | cut -f1)

echo "🔐 Computing checksum..."
CHECKSUM=$(swift package compute-checksum BontechKMP.xcframework.zip)
echo "✅ Checksum: $CHECKSUM"

echo ""
echo "📝 Updating Package.swift with new checksum..."
# Note: You'll need to manually update Package.swift with the new checksum
# or use sed to replace it automatically

echo ""
echo "✅ Ready to create release!"
echo ""
echo "📋 Next steps:"
echo "1. Create a git tag:"
echo "   git tag $VERSION"
echo "   git push origin $VERSION"
echo ""
echo "2. Create release on GitHub:"
echo "   - Go to: https://github.com/mohamedmoussa720/BontechKMP/releases/new"
echo "   - Tag: $VERSION"
echo "   - Title: Release $VERSION"
echo "   - Description: $RELEASE_NOTES"
echo "   - Upload: BontechKMP.xcframework.zip ($ZIP_SIZE)"
echo ""
echo "3. Update Package.swift with:"
echo "   url: \"https://github.com/mohamedmoussa720/BontechKMP/releases/download/$VERSION/BontechKMP.xcframework.zip\""
echo "   checksum: \"$CHECKSUM\""
echo ""
echo "📦 Zip file ready: BontechKMP.xcframework.zip"
echo "🔐 Checksum: $CHECKSUM"

