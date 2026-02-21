plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.ktorfit)
}

kotlin {
    androidLibrary {
        namespace = "com.ai.integrator.core.network"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktorfit.lib)
            implementation(libs.ktorfit.converters.call)
            implementation(libs.ktorfit.converters.response)
            implementation(libs.ktorfit.converters.flow)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            implementation(projects.core.datastore)
        }
    }
}