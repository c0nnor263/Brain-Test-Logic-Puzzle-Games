import java.io.FileInputStream
import java.util.Properties

plugins {
    PluginType.LIBRARY.get(this)
}

val localProperties = Properties()
localProperties.load(FileInputStream(rootProject.file("local.properties")))

val admobPropertiesFile: File = rootProject.file("admob.properties")
val admobProperties = Properties().apply {
    load(FileInputStream(admobPropertiesFile))
}
android {
    namespace = "com.gamovation.core.data"
    compileSdk = Versions.Config.compileSdk

    defaultConfig {
        minSdk = Versions.Config.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        resValue(
            "string",
            "admob_application_id",
            "\"${admobProperties.getProperty("admob_application_id")}\""
        )
        resValue(
            "string",
            "admob_rewarded_id_level_completed_get_extra",
            "\"${admobProperties.getProperty("admob_rewarded_id_level_completed_get_extra")}\""
        )
        resValue(
            "string",
            "admob_rewarded_id_store_screen_watch_ad",
            "\"${admobProperties.getProperty("admob_rewarded_id_store_screen_watch_ad")}\""
        )
        resValue(
            "string",
            "admob_interstitial_id_level_completed",
            "\"${admobProperties.getProperty("admob_interstitial_id_level_completed")}\""
        )
        resValue(
            "string",
            "admob_banner_id_app_bottom_banner",
            "\"${admobProperties.getProperty("admob_banner_id_app_bottom_banner")}\""
        )

        resValue(
            "string",
            "admob_app_open_from_push",
            "\"${admobProperties.getProperty("admob_app_open_from_push")}\""
        )
    }

    buildTypes {
        release {

            isMinifyEnabled = true
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
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:domain"))
    implementation("com.android.billingclient:billing-ktx:${Versions.billing}")
    coreData()

    implementation("com.google.android.play:review:${Versions.playReview}")
    implementation("com.google.android.play:review-ktx:${Versions.playReview}")

    // Play Services Games
    implementation("com.google.android.gms:play-services-games-v2:19.0.0")
}
