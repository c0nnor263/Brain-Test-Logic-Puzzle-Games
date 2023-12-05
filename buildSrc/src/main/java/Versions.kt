import org.gradle.api.JavaVersion

object Versions {
    const val playReview = "2.0.1"
    const val volley = "1.2.1"
    const val oneSignal = "5.0.3"
    const val benNamesVersions = "0.50.0"
    const val ads = "22.5.0"
    const val activity = "1.8.0"
    const val coreKtx = "1.12.0"
    const val billing = "6.1.0"
    const val gradle = "8.2.0"
    const val hilt = "2.48.1"
    const val kotlin = "1.9.10"
    const val immutableCollections = "0.3.6"
    const val coroutines = "1.7.3"
    const val lifecycle = "2.6.2"
    const val excludeFiles = "/META-INF/{AL2.0,LGPL2.1}"
    const val ksp = "1.9.10-1.0.13"
    const val splashScreen = "1.0.1"
    const val profileinstaller = "1.3.1"
    const val startup = "1.1.1"
    const val playIntegrity = "1.3.0"

    object Config {
        const val compileSdk = 34
        const val jvmTarget = "17"
        const val minSdk = 26
        const val targetSdk = 34
        val sourceCompatibility = JavaVersion.VERSION_17
        val targetCompatibility = JavaVersion.VERSION_17
    }

    object Storage {
        const val room = "2.6.0"
    }

    object Firebase {
        const val bom = "32.4.0"
        const val crashlyticsPlugin = "2.9.9"
        const val performancePlugin = "1.4.2"
        const val servicesPlugin = "4.4.0"
    }

    object Compose {
        const val version = "1.5.0"
        const val bom = "2023.10.01"
        const val compiler = "1.5.3"
        const val hiltNavigation = "1.0.0"
        const val navigation = "2.7.0-alpha01"
        const val constraintLayout = "1.0.1"
    }

    object Tooling {
        const val benchmarkMacroJunit4 = "1.2.2"
        const val uiautomator = "2.2.0"
        const val androidEspressoCore = "3.5.1"
        const val androidJunit = "1.1.5"
        const val junit = "4.13.2"

        const val testExtJunit = "1.1.5"
        const val espressoCore = "3.5.1"
    }
}
