plugins {
    alias(libs.plugins.convention.kmp.feature)
}

kotlin {
    androidLibrary {
        namespace = "com.ai.integrator.feature.model"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.data.model)
        }
    }
}