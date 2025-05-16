package convention.ext

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        dependencies {
            "implementation"(platform(libs.findLibrary("androidx-compose-bom").get()))
            "implementation"(libs.findBundle("android-compose").get())

            "debugImplementation"(libs.findLibrary("androidx-compose-ui-tooling").get())
        }
    }
}