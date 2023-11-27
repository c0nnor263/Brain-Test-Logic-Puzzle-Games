import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import java.util.Locale

apply(plugin = "com.github.ben-manes.versions")

plugins {
    kotlin("jvm") version Versions.kotlin apply false
    kotlin("android") version Versions.kotlin apply false
    id("com.android.test") version Versions.gradle apply false
    id("com.android.library") version Versions.gradle apply false
    id("com.android.application") version Versions.gradle apply false
    id("com.google.dagger.hilt.android") version Versions.hilt apply false
    id("com.google.devtools.ksp") version Versions.ksp apply false
    id("com.github.ben-manes.versions") version Versions.benNamesVersions apply false
    id("androidx.baselineprofile") version Versions.Tooling.benchmarkMacroJunit4 apply false
    id("com.google.gms.google-services") version Versions.Firebase.servicesPlugin apply false
    id("com.google.firebase.crashlytics") version Versions.Firebase.crashlyticsPlugin apply false
    id("com.google.firebase.firebase-perf") version Versions.Firebase.performancePlugin apply false
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        val version = candidate.version
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
            version.uppercase(Locale.getDefault())
                .contains(it)
        }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        isStable.not()
    }
}
