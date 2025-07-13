plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.convention.kmp.compose)
}

kotlin {
    androidLibrary {
        namespace = "com.ai.integrator.core.ui"
    }
}