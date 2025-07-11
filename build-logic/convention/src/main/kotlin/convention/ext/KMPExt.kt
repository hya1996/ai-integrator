package convention.ext

import com.android.build.api.dsl.androidLibrary
import Configs
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun KotlinMultiplatformExtension.configureMultiplatform() {
    @Suppress("UnstableApiUsage")
    androidLibrary {
        compileSdk = Configs.COMPILE_SDK_VERSION
        minSdk = Configs.MIN_SDK_VERSION

        compilations.configureEach {
            compilerOptions.configure {
                jvmTarget.set(JvmTarget.JVM_17)
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ai-integrator"
            isStatic = true
        }
    }

    jvm("desktop")
}