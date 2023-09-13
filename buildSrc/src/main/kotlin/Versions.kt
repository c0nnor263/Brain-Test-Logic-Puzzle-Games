import org.gradle.api.JavaVersion

object Versions {
    const val benNamesVersions = "0.48.0"
    const val material = "1.9.0"
    const val ads = "22.3.0"
    const val activity = "1.7.2"
    const val appCompat = "1.6.1"
    const val coreKtx = "1.12.0"
    const val billing = "6.0.1"
    const val gradle = "8.1.1"
    const val hilt = "2.48"
    const val kotlin = "1.9.10"
    const val coroutines = "1.7.3"
    const val lifecycle = "2.6.2"
    const val excludeFiles = "/META-INF/{AL2.0,LGPL2.1}"
    const val ksp = "1.9.10-1.0.13"


    object Config {
        const val compileSdk = 34
        const val jvmTarget = "17"
        const val minSdk = 26
        const val targetSdk = 34
        val sourceCompatibility = JavaVersion.VERSION_17
        val targetCompatibility = JavaVersion.VERSION_17
    }

    object Storage {
        const val room = "2.5.2"
    }

    object Firebase {
        const val bom = "32.2.3"
        const val crashlyticsPlugin = "2.9.9"
        const val performancePlugin = "1.4.2"
        const val servicesPlugin = "4.3.15"
    }

    object Compose {
        const val version = "1.5.0"
        const val bom = "2023.09.00"
        const val compiler = "1.5.3"
        const val hiltNavigation = "1.0.0"
        const val navigation = "2.7.0-alpha01"
        val constraintLayout = "1.0.1"
    }

    object Tooling {
        const val benchmarkMacroJunit4 = "1.2.0-beta03"
        const val uiautomator = "2.2.0"
        const val androidEspressoCore = "3.5.1"
        const val androidJunit = "1.1.5"
        const val junit = "4.13.2"
    }
}