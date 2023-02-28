@file:Suppress("UnstableApiUsage")

import java.io.FileInputStream
import java.util.Properties

plugins {
    PluginType.APPLICATION.get(this)
}


val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties().apply {
    load(FileInputStream(keystorePropertiesFile))
}

android {
    namespace = "com.conboi.wordefull"
    compileSdk = Versions.Config.compileSdk

    defaultConfig {
        applicationId = namespace
        minSdk = Versions.Config.minSdk
        targetSdk = Versions.Config.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Versions.Config.sourceCompatibility
        targetCompatibility = Versions.Config.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Versions.Config.jvmTarget
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose.compiler
    }
    packagingOptions {
        resources {
            resources.excludes.add(Versions.excludeFiles)
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":feature:home"))

    composeCore()


    // Lifecycle + ViewModel
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}")


    // Room
    ksp("androidx.room:room-compiler:${Versions.Storage.room}")
    implementation("androidx.room:room-runtime:${Versions.Storage.room}")
    implementation("androidx.room:room-ktx:${Versions.Storage.room}")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:${Versions.Compose.navigation}")
    implementation("androidx.hilt:hilt-navigation-compose:${Versions.Compose.hiltNavigation}")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:${Versions.Firebase.firebaseBom}"))
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-analytics")

}