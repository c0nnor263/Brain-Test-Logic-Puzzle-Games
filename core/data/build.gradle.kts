@file:Suppress("UnstableApiUsage")

import java.io.FileInputStream
import java.util.Properties

plugins {
    PluginType.LIBRARY.get(this)
}


val localProperties = Properties()
localProperties.load(FileInputStream(rootProject.file("local.properties")))
android {
    namespace = "com.conboi.core.data"
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
        buildConfigField("String", "authKey", "\"${localProperties.getProperty("authKey")}\"")
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

    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:${Versions.material}")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.android.billingclient:billing-ktx:${Versions.billing}")
    implementation("com.android.volley:volley:1.2.1")
    coreData()
}
