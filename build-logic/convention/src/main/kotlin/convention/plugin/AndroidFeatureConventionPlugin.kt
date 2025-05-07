package convention.plugin

import Modules
import convention.ext.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("convention.android.library")
                apply("convention.android.library.compose")
            }

            dependencies {
                "implementation"(libs.findLibrary("coil-compose").get())
                "implementation"(libs.findLibrary("coil-network-okhttp").get())

                "implementation"(project(Modules.Core.ui))
            }
        }
    }
}