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
            implementation(projects.core.datastore)
            implementation(projects.core.user)
            api(projects.core.im)

            implementation(projects.data.model)
        }
    }
}