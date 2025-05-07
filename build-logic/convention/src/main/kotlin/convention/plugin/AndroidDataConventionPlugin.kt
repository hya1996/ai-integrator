package convention.plugin

import Modules
import convention.ext.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class AndroidDataConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("convention.android.library")
            }

            dependencies {
                "implementation"(libs.findLibrary("room-runtime").get())
                "implementation"(libs.findLibrary("room-ktx").get())

                "implementation"(project(Modules.Core.network))

                "ksp"(libs.findLibrary("room-compiler").get())

                "testImplementation"(libs.findLibrary("room-testing").get())
            }
        }
    }
}