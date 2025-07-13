plugins {
    alias(libs.plugins.convention.kmp.library)
}

kotlin {
    androidLibrary {
        namespace = "com.ai.integrator.core.im"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.room.runtime)

            implementation(project(Modules.Core.user))
        }
    }
}