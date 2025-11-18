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
        .target(
            name: "BontechKMP",
            path: "Sources/BontechKMP"
        )
    ]
)