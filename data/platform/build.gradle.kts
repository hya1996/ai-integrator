plugins {
    alias(libs.plugins.convention.kmp.data)
}

kotlin {
    androidLibrary {
        namespace = "com.ai.integrator.data.platform"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(Modules.Core.datastore))
        }
    }
}