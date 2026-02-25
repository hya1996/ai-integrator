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
            implementation(projects.core.datastore)
            implementation(projects.core.ui)

            implementation(projects.feature.model)
            implementation(projects.feature.dialogue)
        }
    }
}