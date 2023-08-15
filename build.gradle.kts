import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import java.util.Locale

apply(plugin = "com.github.ben-manes.versions")

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.gradle}")
        classpath("com.github.ben-manes:gradle-versions-plugin:0.47.0")
    }
}

plugins {
    kotlin("jvm") version Versions.kotlin apply false
    kotlin("android") version Versions.kotlin apply false
    id("com.android.library") version Versions.gradle apply false
    id("com.android.application") version Versions.gradle apply false
    id("com.google.gms.google-services") version Versions.Firebase.servicesPlugin apply false
    id("com.google.firebase.crashlytics") version Versions.Firebase.crashlyticsPlugin apply false
    id("com.google.firebase.firebase-perf") version Versions.Firebase.performancePlugin apply false
    id("com.google.dagger.hilt.android") version Versions.hilt apply false
}



fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
        version.uppercase(Locale.getDefault())
            .contains(it)
    }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}
tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}