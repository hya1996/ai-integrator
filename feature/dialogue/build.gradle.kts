plugins {
    alias(libs.plugins.convention.kmp.feature)
}

kotlin {
    androidLibrary {
        namespace = "com.ai.integrator.feature.dialogue"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(Modules.Core.user))
            implementation(project(Modules.Data.dialogue))
        }
    }
}