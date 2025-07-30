plugins {
    alias(libs.plugins.convention.kmp.data)
    alias(libs.plugins.convention.kmp.room)
}

kotlin {
    androidLibrary {
        namespace = "com.ai.integrator.data.dialogue"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(Modules.Core.datastore))
            implementation(project(Modules.Core.user))
            api(project(Modules.Core.im))
        }
    }
}