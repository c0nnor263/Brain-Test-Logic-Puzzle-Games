import org.gradle.api.JavaVersion

object Versions {
    const val ads = "21.5.0"
    const val activity = "1.6.1"
    const val coreKtx = "1.9.0"
    const val gradle = "7.4.1"
    const val hilt = "2.45"
    const val kotlin = "1.8.10"
    const val coroutines = "1.6.4"
    const val lifecycle = "2.6.1"
    const val excludeFiles = "/META-INF/{AL2.0,LGPL2.1}"
    const val ksp = "1.8.10-1.0.9"


    object Config {
        const val compileSdk = 33
        const val jvmTarget = "11"
        const val minSdk = 26
        const val targetSdk = 33
        val sourceCompatibility = JavaVersion.VERSION_11
        val targetCompatibility = JavaVersion.VERSION_11
    }

    object Storage {
        const val room = "2.5.0"
    }

    object Firebase {
        const val firebaseBom = "31.2.3"
        const val firebaseCrashlyticsPlugin = "2.9.4"
        const val googleServicesPlugin = "4.3.15"
    }

    object Compose {
        const val version = "1.4.0"
        const val bom = "2023.03.00"
        const val compiler = "1.4.4"
        const val hiltNavigation = "1.0.0"
        const val navigation = "2.5.3"
        val constraintLayout = "1.0.1"
    }

    object Tooling {
        const val androidEspressoCore = "3.5.1"
        const val androidJunit = "1.1.5"
        const val junit = "4.13.2"
    }
}