plugins {
    alias(libs.plugins.convention.kmp.feature)
}

kotlin {
    androidLibrary {
        namespace = "com.ai.integrator.feature.platform"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.data.platform)
        }
    }
}