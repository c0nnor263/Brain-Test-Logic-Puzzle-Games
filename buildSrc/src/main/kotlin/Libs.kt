import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec


fun DependencyHandler.composeCore() {
    add(LibType.DEFAULT.value, "androidx.core:core-ktx:${Versions.coreKtx}")
    add(LibType.DEFAULT.value, "androidx.activity:activity-compose:${Versions.activity}")
    add(LibType.DEFAULT.value, platform("androidx.compose:compose-bom:${Versions.Compose.bom}"))
    add(LibType.DEFAULT.value, "androidx.compose.ui:ui")
    add(LibType.DEFAULT.value, "androidx.compose.ui:ui-graphics")
    add(LibType.DEFAULT.value, "androidx.compose.ui:ui-tooling-preview")
    add(LibType.DEFAULT.value, "androidx.compose.material3:material3")
    add(LibType.DEFAULT.value, "androidx.compose.material:material-icons-extended")
    add(LibType.TEST.value, "junit:junit:${Versions.Tooling.junit}")
    add(LibType.ANDROID_TEST.value, "androidx.test.ext:junit:${Versions.Tooling.androidJunit}")
    add(
        LibType.ANDROID_TEST.value,
        "androidx.test.espresso:espresso-core:${Versions.Tooling.androidEspressoCore}"
    )
    add(
        LibType.ANDROID_TEST.value,
        platform("androidx.compose:compose-bom:${Versions.Compose.bom}")
    )
    add(LibType.ANDROID_TEST.value, "androidx.compose.ui:ui-test-junit4")
    add(LibType.DEBUG.value, "androidx.compose.ui:ui-tooling")
    add(LibType.DEBUG.value, "androidx.compose.ui:ui-test-manifest")


    add(LibType.DEFAULT.value, "com.google.dagger:hilt-android:${Versions.hilt}")
    add(LibType.KAPT.value, "com.google.dagger:hilt-android-compiler:${Versions.hilt}")
}




fun PluginType.get(scope: PluginDependenciesSpec) {
    with(scope) {
        when (this@get) {
            PluginType.APPLICATION -> {
                id("com.android.application")
                kotlin("android")
                id("kotlin-kapt")
                id("com.google.dagger.hilt.android")
                id("com.google.gms.google-services")
                id("com.google.firebase.crashlytics")
                id("com.google.devtools.ksp") version Versions.ksp
            }

            else -> {
                id("com.android.library")
                kotlin("android")
                id("kotlin-kapt")
                id("com.google.dagger.hilt.android")
                id("com.google.devtools.ksp") version Versions.ksp
            }
        }
    }
}











