package convention.plugin

import com.android.build.api.dsl.androidLibrary
import convention.ext.configureAndroidLibrary
import convention.ext.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KMPFrameworkConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.kotlin.multiplatform.library")
                apply("org.jetbrains.kotlin.multiplatform")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("com.google.devtools.ksp")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                @Suppress("UnstableApiUsage")
                androidLibrary {
                    configureAndroidLibrary()
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

                sourceSets.commonMain.dependencies {
                    implementation(project.dependencies.platform(libs.findLibrary("koin-bom").get()))
                    implementation(libs.findBundle("common-base").get())
                }
            }
        }
    }
}