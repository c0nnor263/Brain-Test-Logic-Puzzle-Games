plugins {
    PluginType.LIBRARY.get(this)
}

android {
    namespace = "com.gamovation.feature.level"
    compileSdk = Versions.Config.compileSdk

    defaultConfig {
        minSdk = Versions.Config.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }
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

    composeCore()

    implementation(project(":feature:level:level1"))
    implementation(project(":feature:level:level2"))
    implementation(project(":feature:level:level3"))
    implementation(project(":feature:level:level4"))
    implementation(project(":feature:level:level5"))
    implementation(project(":feature:level:level6"))
    implementation(project(":feature:level:level7"))
    implementation(project(":feature:level:level8"))
    implementation(project(":feature:level:level9"))
    implementation(project(":feature:level:level10"))
    implementation(project(":feature:level:level11"))
    implementation(project(":feature:level:level12"))
    implementation(project(":feature:level:level13"))
    implementation(project(":feature:level:level14"))
    implementation(project(":feature:level:level15"))
    implementation(project(":feature:level:level16"))
    implementation(project(":feature:level:level17"))
    implementation(project(":feature:level:level18"))
    implementation(project(":feature:level:level19"))
    implementation(project(":feature:level:level20"))
}
