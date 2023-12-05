import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.plugin.use.PluginDependenciesSpec

fun PluginType.get(scope: PluginDependenciesSpec) {
    with(scope) {
        val plugins = get()
        plugins.forEach {
            id(it)
        }
    }
}

fun PluginType.get(): List<String> {
    return when (this) {
        PluginType.APPLICATION -> {
            listOf(
                "com.android.application",
                "org.jetbrains.kotlin.android",
                "com.google.devtools.ksp",
                "com.google.gms.google-services",
                "com.google.firebase.crashlytics",
                "com.google.firebase.firebase-perf",
                "com.google.dagger.hilt.android",
                "androidx.baselineprofile"
            )
        }

        else -> {
            listOf(
                "com.android.library",
                "org.jetbrains.kotlin.android",
                "com.google.devtools.ksp",
                "com.google.dagger.hilt.android"
            )
        }
    }
}

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
    add(LibType.DEFAULT.value, "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}")

    add(
        LibType.DEFAULT.value,
        "androidx.navigation:navigation-compose:${Versions.Compose.navigation}"
    )
    add(
        LibType.DEFAULT.value,
        "androidx.hilt:hilt-navigation-compose:${Versions.Compose.hiltNavigation}"
    )

    add(
        LibType.DEFAULT.value,
        "androidx.constraintlayout:constraintlayout-compose:${Versions.Compose.constraintLayout}"
    )

    add(LibType.DEFAULT.value, "com.google.dagger:hilt-android:${Versions.hilt}")
    add(LibType.KSP.value, "com.google.dagger:hilt-android-compiler:${Versions.hilt}")

    add(
        LibType.DEFAULT.value,
        "org.jetbrains.kotlinx:kotlinx-collections-immutable:${Versions.immutableCollections}"
    )
}

fun DependencyHandler.coreData() {
    // Room
    add(LibType.DEFAULT.value, "androidx.room:room-runtime:${Versions.Storage.room}")
//    add(LibType.ANNOTATION_PROCESSOR.value, "androidx.room:room-compiler:${Versions.Storage.room}")
    add(LibType.KSP.value, "androidx.room:room-compiler:${Versions.Storage.room}")
    add(LibType.DEFAULT.value, "androidx.room:room-ktx:${Versions.Storage.room}")

    add(LibType.DEFAULT.value, "com.google.dagger:hilt-android:${Versions.hilt}")
    add(LibType.KSP.value, "com.google.dagger:hilt-android-compiler:${Versions.hilt}")

    add(LibType.DEFAULT.value, "com.google.android.gms:play-services-ads:${Versions.ads}")
    add(LibType.DEFAULT.value, "androidx.datastore:datastore-preferences:1.0.0")
}
