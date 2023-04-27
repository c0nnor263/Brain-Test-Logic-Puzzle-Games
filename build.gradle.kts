import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

apply(plugin = "com.github.ben-manes.versions")

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}")
        classpath("com.google.firebase:firebase-crashlytics-gradle:${Versions.Firebase.firebaseCrashlyticsPlugin}")
        classpath("com.google.gms:google-services:${Versions.Firebase.googleServicesPlugin}")
        classpath("com.github.ben-manes:gradle-versions-plugin:0.46.0")
    }
}

plugins {
    id("com.android.application") version Versions.gradle apply false
    id("com.android.library") version Versions.gradle apply false
    kotlin("android") version Versions.kotlin apply false
    kotlin("jvm") version Versions.kotlin apply false
}



fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}
tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}