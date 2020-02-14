// swift-tools-version:5.0
import Foundation
import PackageDescription

let packageName = "FlankerKitCore"

// generated sources integration
let generatedName = "Generated"
let generatedPath = ".build/\(generatedName.lowercased())"

let isSourcesGenerated: Bool = {
    let basePath = URL(fileURLWithPath: #file)
            .deletingLastPathComponent()
            .path

    let fileManager = FileManager()
    fileManager.changeCurrentDirectoryPath(basePath)

    var isDirectory: ObjCBool = false
    let exists = fileManager.fileExists(atPath: generatedPath, isDirectory: &isDirectory)

    return exists && isDirectory.boolValue
}()

func addGenerated(_ products: [Product]) -> [Product] {
    if isSourcesGenerated == false {
        return products
    }

    return products + [
        .library(name: packageName, type: .dynamic, targets: [generatedName])
    ]
}

func addGenerated(_ targets: [Target]) -> [Target] {
    if isSourcesGenerated == false {
        return targets
    }

    return targets + [
        .target(
            name: generatedName,
            dependencies: [
              .byName(name: packageName)
            ],
            path: generatedPath
        )
    ]
}
// generated sources integration end

let package = Package(
    name: packageName,
    products: addGenerated([
    ]),
    dependencies: [
        // .package(url: "https://github.com/kastelolabs/java_swift.git", .upToNextMinor(from: "2.1.6")),
        // .package(url: "https://github.com/kastelolabs/java_swift.git", .branch("swift5")),
        .package(url: "https://github.com/readdle/java_swift", .upToNextMinor(from: "2.1.8")),
        // .package(url: "../../../../../../../../../swift-java", .branch("swift5")),
        .package(url: "https://github.com/readdle/swift-java", .upToNextMinor(from: "0.2.2")),
        // .package(url: "../../../../../../../../../swift-java-coder", .branch("swift5")),
        .package(url: "https://github.com/readdle/swift-java-coder", .upToNextMinor(from: "1.0.15")),
        .package(url: "https://github.com/readdle/swift-anycodable.git", .upToNextMinor(from: "1.0.3")),
        // .package(url: "ssh://git@stash.nubic.northwestern.edu:7999/mss/mobiletoolbox.git", .branch("feature/spm-integration")),
        // .package(url: "ssh://git@stash.nubic.northwestern.edu:7999/mss/mssenginekit.git", .branch("feature/spm-integration")),

        .package(url: "../../../../../mobiletoolbox/", .branch("feature/spm-integration")),
        // .package(url: "../../../../../mssenginekit/", .branch("feature/spm-integration")),

    ],
    targets: addGenerated([
        .target(name: packageName, dependencies: ["java_swift", "Java", "JavaCoder", "AnyCodable", "FlankerKit"])
    ]),
    swiftLanguageVersions: [.v5]
)
