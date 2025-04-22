plugins {
    alias(libs.plugins.convention.android.library)
}

android {
    namespace = "com.ai.integrator.core.im"
}

dependencies {
    implementation(project(Modules.Core.user))
}