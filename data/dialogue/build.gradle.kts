plugins {
    alias(libs.plugins.convention.android.data)
}

android {
    namespace = "com.ai.integrator.data.dialogue"
}

dependencies {
    implementation(project(Modules.Core.user))
    api(project(Modules.Core.im))
}