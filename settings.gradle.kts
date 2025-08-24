pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ai-integrator"

include(":app:androidApp")
include(":app:desktopApp")

include(":shared")

include(":core:framework")
include(":core:datastore")
include(":core:network")
include(":core:ui")
include(":core:user")
include(":core:im")

include(":data:platform")
include(":data:dialogue")

include(":feature:platform")
include(":feature:dialogue")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")