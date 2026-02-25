plugins {
    alias(libs.plugins.convention.kmp.feature)
}

kotlin {
    androidLibrary {
        namespace = "com.ai.integrator.feature.dialogue"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.user)

            implementation(projects.data.model)
            implementation(projects.data.dialogue)
        }
    }
}