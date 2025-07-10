package convention.ext

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import convention.Configs
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

fun KotlinMultiplatformAndroidLibraryTarget.configureAndroidLibrary() {
    compileSdk = Configs.COMPILE_SDK_VERSION
    minSdk = Configs.MIN_SDK_VERSION

    compilations.configureEach {
        compilerOptions.configure {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
}