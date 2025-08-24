plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.convention.kmp.compose)
}

kotlin {
    androidLibrary {
        namespace = "com.ai.integrator.shared"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(Modules.Core.datastore))
            implementation(project(Modules.Core.ui))

            implementation(project(Modules.Feature.platform))
            implementation(project(Modules.Feature.dialogue))
        }
    }
}