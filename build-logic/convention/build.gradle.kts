import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.ai.integrator.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.compose.gradle.plugin)
    compileOnly(libs.room.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("conventionKMPFramework") {
            id = "convention.kmp.framework"
            implementationClass = "convention.plugin.KMPFrameworkConventionPlugin"
        }

        register("conventionKMPLibrary") {
            id = "convention.kmp.library"
            implementationClass = "convention.plugin.KMPLibraryConventionPlugin"
        }

        register("conventionKMPCompose") {
            id = "convention.kmp.compose"
            implementationClass = "convention.plugin.KMPComposeConventionPlugin"
        }

        register("conventionKMPFeature") {
            id = "convention.kmp.feature"
            implementationClass = "convention.plugin.KMPFeatureConventionPlugin"
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