package convention.ext

import Configs
import com.android.build.api.dsl.androidLibrary
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun KotlinMultiplatformExtension.configureMultiplatform() {
    @Suppress("UnstableApiUsage")
    androidLibrary {
        compileSdk = Configs.COMPILE_SDK_VERSION
        minSdk = Configs.MIN_SDK_VERSION

        compilations.configureEach {
            compilerOptions.configure {
                jvmTarget.set(JvmTarget.JVM_21)
            }
        }

        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    jvm("desktop")
}