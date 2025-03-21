plugins {
    alias(libs.plugins.convention.android.library)
}

android {
    namespace = "com.ai.integrator.core.network"
}

dependencies {
    api(libs.retrofit)
    api(libs.retrofit.converter.gson)
}