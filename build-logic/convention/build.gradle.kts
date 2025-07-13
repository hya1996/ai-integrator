import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.ai.integrator.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.compose.gradle.plugin)
    compileOnly(libs.androidx.room.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("conventionKMPFramework") {
            id = "convention.kmp.framework"
            implementationClass = "convention.plugin.KMPFrameworkConventionPlugin"
        }

        register("conventionAndroidApplication") {
            id = "convention.android.application"
            implementationClass = "convention.plugin.AndroidApplicationConventionPlugin"
        }

        register("conventionAndroidLibrary") {
            id = "convention.android.library"
            implementationClass = "convention.plugin.AndroidLibraryConventionPlugin"
        }

        register("conventionKMPLibrary") {
            id = "convention.kmp.library"
            implementationClass = "convention.plugin.KMPLibraryConventionPlugin"
        }

        register("conventionKMPCompose") {
            id = "convention.kmp.compose"
            implementationClass = "convention.plugin.KMPComposeConventionPlugin"
        }

        register("conventionAndroidApplicationCompose") {
            id = "convention.android.application.compose"
            implementationClass = "convention.plugin.AndroidApplicationComposeConventionPlugin"
        }

        register("conventionAndroidLibraryCompose") {
            id = "convention.android.library.compose"
            implementationClass = "convention.plugin.AndroidLibraryComposeConventionPlugin"
        }

        register("conventionAndroidFeature") {
            id = "convention.android.feature"
            implementationClass = "convention.plugin.AndroidFeatureConventionPlugin"
        }

        register("conventionKMPData") {
            id = "convention.kmp.data"
            implementationClass = "convention.plugin.KMPDataConventionPlugin"
        }

        register("conventionKMPRoom") {
            id = "convention.kmp.room"
            implementationClass = "convention.plugin.KMPRoomConventionPlugin"
        }
    }
}