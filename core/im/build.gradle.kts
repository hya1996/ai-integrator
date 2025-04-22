plugins {
    alias(libs.plugins.convention.android.library)
}

android {
    namespace = "com.ai.integrator.core.im"
}

dependencies {
    implementation(Modules.Core.user)
}