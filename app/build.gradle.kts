import java.io.FileInputStream
import java.util.Properties

plugins {
    PluginType.APPLICATION.get(this)
}

// commonAndroid(
//    configPluginType = PluginType.APPLICATION,
//    configNamespace = "com.gamovation.tilecl",
//    configVersionCode = 23,
//    configVersionName = "23.11.29.1"
// )

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
        versionCode = 23
        versionName = "2312314125"

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

    compileOptions {
        sourceCompatibility = Versions.Config.sourceCompatibility
        targetCompatibility = Versions.Config.targetCompatibility
    }
}

dependencies {
    add(
        JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME,
        project(":core:data")
    )
    add(
        JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME,
        project(":core:database")
    )
    add(
        JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME,
        project(":core:domain")
    )
    add(
        JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME,
        project(":core:ui")
    )
    add(
        JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME,
        project(":core:navigation")
    )
    add(
        JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME,
        project(":feature:home")
    )
    add(
        JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME,
        project(":feature:menu")
    )
    add(
        JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME,
        project(":feature:store")
    )
    add(
        JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME,
        project(":feature:level")
    )
    add(
        JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME,
        project(":feature:settings")
    )
    add("baselineProfile", project(":baselineprofile"))
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
