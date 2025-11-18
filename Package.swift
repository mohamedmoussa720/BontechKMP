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
            path: "./BontechKMP.xcframework"
        )
    ]
)
