@file:Suppress("UnstableApiUsage")

import java.io.FileInputStream
import java.util.Properties

plugins {
    PluginType.APPLICATION.get(this)
    id("androidx.baselineprofile")
}


val keystorePropertiesFile: File = rootProject.file("keystore.properties")
val keystoreProperties = Properties().apply {
    load(FileInputStream(keystorePropertiesFile))
}

android {
    namespace = "com.gamovation.tilecl"
    compileSdk = Versions.Config.compileSdk

    defaultConfig {
        applicationId = namespace
        minSdk = Versions.Config.minSdk
        targetSdk = Versions.Config.targetSdk
        versionCode = 20
        versionName = "23.11.20.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        resourceConfigurations.addAll(
            listOf(
                "en",
                "de",
                "fr",
                "es",
                "it",
                "pt",
                "ru",
                "uk"
            )
        )
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
        debug {
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
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
    packaging {
        resources {
            resources.excludes.add(Versions.excludeFiles)
        }
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:database"))
    implementation(project(":core:domain"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":feature:home"))
    implementation(project(":feature:menu"))
    implementation(project(":feature:store"))
    implementation(project(":feature:level"))
    implementation(project(":feature:settings"))
    "baselineProfile"(project(":baselineprofile"))
    composeCore()


    // Lifecycle + ViewModel
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:${Versions.Firebase.bom}"))
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-perf-ktx")



    implementation("com.android.billingclient:billing-ktx:${Versions.billing}")

    implementation("androidx.core:core-splashscreen:${Versions.splashScreen}")

    implementation("androidx.profileinstaller:profileinstaller:${Versions.profileinstaller}")
    implementation("androidx.startup:startup-runtime:${Versions.startup}")
    implementation("com.google.android.gms:play-services-ads:${Versions.ads}")
    implementation("com.google.android.play:integrity:${Versions.playIntegrity}")

    // OneSignal
    implementation("com.onesignal:OneSignal:${Versions.oneSignal}")

}