package convention.plugin

import androidx.room.gradle.RoomExtension
import convention.ext.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KMPRoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("androidx.room")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.commonMain.dependencies {
                    implementation(libs.findLibrary("room-runtime").get())
                    implementation(libs.findLibrary("room-paging").get())
                    implementation(libs.findLibrary("sqlite-bundled").get())
                }
            }

            extensions.configure<RoomExtension> {
                schemaDirectory("$projectDir/schemas")
            }

            dependencies {
                val roomCompilerLib = libs.findLibrary("room-compiler").get()
                add("kspAndroid", roomCompilerLib)
                add("kspIosArm64", roomCompilerLib)
                add("kspIosSimulatorArm64", roomCompilerLib)
                add("kspDesktop", roomCompilerLib)
            }
        }
    }
}