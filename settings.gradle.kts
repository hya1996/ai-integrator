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

include(":platform:androidApp")
include(":platform:desktopApp")

include(":app")

include(":core:framework")
include(":core:datastore")
include(":core:network")
include(":core:ui")
include(":core:user")
include(":core:im")

include(":feature:dialogue")

include(":data:dialogue")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")