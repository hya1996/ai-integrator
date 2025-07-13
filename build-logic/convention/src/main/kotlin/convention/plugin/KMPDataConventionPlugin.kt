package convention.plugin

import Modules
import convention.ext.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KMPDataConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("convention.kmp.library")
                apply("de.jensklingenberg.ktorfit")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.commonMain.dependencies {
                    implementation(libs.findLibrary("ktorfit-lib").get())
                    implementation(project(Modules.Core.network))
                }
            }
        }
    }
}