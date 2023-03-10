rootProject.name = "hyacinth"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("junit", "5.9.1")

            library("junit-api", "org.junit.jupiter", "junit-jupiter-api")
                    .versionRef("junit")
            library("junit-engine", "org.junit.jupiter", "junit-jupiter-engine")
                    .versionRef("junit")

            library("assertj", "org.assertj:assertj-core:3.23.1")
            library("gson", "com.google.code.gson:gson:2.10")
        }
    }
}

include("hyacinth-core")
include("hyacinth-cli")
