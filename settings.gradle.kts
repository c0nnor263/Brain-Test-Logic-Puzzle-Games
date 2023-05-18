@file:Suppress("UnstableApiUsage")

include(":feature:store")


include(":feature:settings")








pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Wordefull"
include(":app")
include(":core:database")
include(":core:data")
include(":core:domain")
include(":core:ui")
include(":core:navigation")
include(":feature:home")
include(":feature:menu")
include(":feature:level")



