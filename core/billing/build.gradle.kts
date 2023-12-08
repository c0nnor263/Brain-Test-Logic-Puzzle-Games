import java.io.FileInputStream
import java.util.Properties

plugins {
    PluginType.LIBRARY.get(this)
}

val localProperties = Properties()
localProperties.load(FileInputStream(rootProject.file("local.properties")))

android {
    namespace = "com.gamovation.core.billing"
    compileSdk = Versions.Config.compileSdk

    defaultConfig {
        minSdk = Versions.Config.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField(
            "String",
            "verifyPurchases",
            "\"${localProperties.getProperty("verifyPurchases")}\""
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
    implementation(project(":core:data"))
    implementation(project(":core:database"))
    implementation(project(":core:domain"))
    implementation("com.android.billingclient:billing-ktx:${Versions.billing}")
    implementation("com.android.volley:volley:${Versions.volley}")
    coreData()

    implementation("com.google.android.play:review:${Versions.playReview}")
    implementation("com.google.android.play:review-ktx:${Versions.playReview}")
}
