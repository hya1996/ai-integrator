plugins {
    alias(libs.plugins.convention.kmp.library)
}

kotlin {
    androidLibrary {
        namespace = "com.ai.integrator.core.network"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            implementation(projects.core.datastore)
        }
    }
}