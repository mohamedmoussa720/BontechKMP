// swift-tools-version:5.9
import PackageDescription

let package = Package(
    name: "BontechKMP",
    platforms: [
        .iOS(.v14),
        .macOS(.v11)
    ],
    products: [
        .library(
            name: "BontechKMP",
            targets: ["BontechKMP"]
        ),
    ],
    targets: [
        .binaryTarget(
            name: "BontechKMP",
            url: "https://github.com/mohamedmoussa720/BontechKMP/releases/download/1.0.0/BontechKMP.xcframework.zip",
            checksum: "6b50b227e558c090f40da385626221925c85e33a5daffd479093c62e16504dbc"
        )
    ]
)
