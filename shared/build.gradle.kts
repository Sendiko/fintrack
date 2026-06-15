import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

compose {
    resources {
        packageOfResClass = "fintrack.composeapp.generated.resources"
        generateResClass = auto
    }
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate()

    androidLibrary {
        namespace = "id.my.sendiko.fintrack"
        compileSdk = libs.versions.android.compileSdk.get().toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
        androidResources {
            enable = true
        }
        withHostTest {
            isIncludeAndroidResources = true
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.compose.materialIconsExtended)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.navigation.compose)
            implementation(libs.kotlinx.datetime)

            /* DataStore */
            api(libs.datastore)
            api(libs.datastore.preferences)

            /* Serialization Json */
            implementation(libs.kotlinx.serialization.json)

            /* Koin */
            implementation(libs.koin.compose.viewmodel)
            api(libs.koin.core)

            /* Ktor */
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.auth)
        }

        iosMain.dependencies {
            /* Ktor Darwin */
            implementation(libs.ktor.client.darwin)
        }

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            /* Koin Android */
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)

            /* Ktor OkHttp */
            implementation(libs.ktor.client.okhttp)

            /* Google MLKit */
            implementation(libs.google.mlkit)
            implementation(libs.vision.common)

            /* Kotlin Coroutines */
            implementation(libs.kotlinx.coroutines.play.services) // Use your project's coroutines version
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}