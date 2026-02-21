plugins {
    alias(libs.plugins.convention.kmp.data)
    alias(libs.plugins.convention.kmp.room)
}

kotlin {
    androidLibrary {
        namespace = "com.ai.integrator.data.platform"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.datastore)
        }
    }
}