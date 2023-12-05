// import com.android.build.api.dsl.ApplicationExtension
// import com.android.build.gradle.LibraryExtension
// import java.io.File
// import java.io.FileInputStream
// import java.util.Properties
// import org.gradle.api.Project
// import org.gradle.api.plugins.JavaPlugin
// import org.gradle.kotlin.dsl.configure
// import org.gradle.kotlin.dsl.project
//
// fun Project.commonAndroid(
//    configPluginType: PluginType = PluginType.LIBRARY,
//    configNamespace: String?,
//    configVersionCode: Int? = null,
//    configVersionName: String? = null
// ) {
//    when (configPluginType) {
//        PluginType.LIBRARY -> configureLibrary(configNamespace)
//        PluginType.APPLICATION -> configureApplication(
//            configNamespace,
//            configVersionCode,
//            configVersionName
//        )
//    }
//    dependencies.add("implementation", project(":core:data"))
//    dependencies.add("implementation", project(":core:domain"))
//    dependencies.add("implementation", project(":core:ui"))
//    dependencies.composeCore()
// }
//
// private fun Project.configureLibrary(configNamespace: String?) {
//    extensions.configure<LibraryExtension> {
//        namespace = configNamespace
//        compileSdk = Versions.Config.compileSdk
//
//        defaultConfig {
//            minSdk = Versions.Config.minSdk
//            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//            consumerProguardFiles("consumer-rules.pro")
//            vectorDrawables {
//                useSupportLibrary = true
//            }
//        }
//        buildTypes {
//            release {
//                isMinifyEnabled = true
//                proguardFiles(
//                    getDefaultProguardFile("proguard-android-optimize.txt"),
//                    "proguard-rules.pro"
//                )
//            }
//        }
//
//        buildFeatures {
//            compose = true
//        }
//        composeOptions {
//            kotlinCompilerExtensionVersion = Versions.Compose.compiler
//        }
//        packaging {
//            resources {
//                resources.excludes.add(Versions.excludeFiles)
//            }
//        }
//
//        compileOptions {
//            sourceCompatibility = Versions.Config.sourceCompatibility
//            targetCompatibility = Versions.Config.targetCompatibility
//        }
//    }
// }
//
// private fun Project.configureApplication(
//    configNamespace: String?,
//    configVersionCode: Int?,
//    configVersionName: String?
// ) {
//    val keystorePropertiesFile: File = rootProject.file("keystore.properties")
//    val keystoreProperties = Properties().apply {
//        load(FileInputStream(keystorePropertiesFile))
//    }
//    extensions.configure<ApplicationExtension> {
//        namespace = configNamespace
//        compileSdk = Versions.Config.compileSdk
//
//        defaultConfig {
//            applicationId = configNamespace
//            minSdk = Versions.Config.minSdk
//            targetSdk = Versions.Config.targetSdk
//            versionCode = configVersionCode
//            versionName = configVersionName
//
//            resourceConfigurations.addAll(
//                listOf(
//                    "en",
//                    "de",
//                    "fr",
//                    "es",
//                    "it",
//                    "pt",
//                    "ru",
//                    "uk"
//                )
//            )
//
//            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//            vectorDrawables {
//                useSupportLibrary = true
//            }
//        }
//
//        signingConfigs {
//            create("release") {
//                keyAlias = keystoreProperties["keyAlias"] as String
//                keyPassword = keystoreProperties["keyPassword"] as String
//                storeFile = file(keystoreProperties["storeFile"] as String)
//                storePassword = keystoreProperties["storePassword"] as String
//            }
//        }
//
//        buildTypes {
//            debug {
//                applicationIdSuffix = ".debug"
//            }
//            release {
//                isMinifyEnabled = true
//                isShrinkResources = true
//                proguardFiles(
//                    getDefaultProguardFile("proguard-android-optimize.txt"),
//                    "proguard-rules.pro"
//                )
//                signingConfig = signingConfigs.getByName("release")
//            }
//        }
//
//        buildFeatures {
//            compose = true
//        }
//        composeOptions {
//            kotlinCompilerExtensionVersion = Versions.Compose.compiler
//        }
//        packaging {
//            resources {
//                resources.excludes.add(Versions.excludeFiles)
//            }
//        }
//
//        compileOptions {
//            sourceCompatibility = Versions.Config.sourceCompatibility
//            targetCompatibility = Versions.Config.targetCompatibility
//        }
//
//        dependencies.apply {
//            add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, project(":core:data"))
//            add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, project(":core:database"))
//            add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, project(":core:domain"))
//            add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, project(":core:ui"))
//            add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, project(":core:navigation"))
//            add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, project(":feature:home"))
//            add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, project(":feature:menu"))
//            add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, project(":feature:store"))
//            add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, project(":feature:level"))
//            add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, project(":feature:settings"))
//            add("baselineProfile", project(":baselineprofile"))
//            composeCore()
//        }
//    }
// }
