plugins {
    alias(libs.plugins.convention.android.library)
}

android {
    namespace = "com.ai.integrator.core.im"
}

dependencies {
    implementation(libs.room.runtime)

    implementation(project(Modules.Core.user))
}