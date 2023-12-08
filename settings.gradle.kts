@file:Suppress("UnstableApiUsage")

include(":core:billing")

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
include(":feature:level:level1")
include(":feature:level:level2")
include(":feature:level:level3")
include(":feature:level:level4")
include(":feature:level:level5")
include(":feature:level:level6")
include(":feature:level:level7")
include(":feature:level:level8")
include(":feature:level:level9")
include(":feature:level:level10")
include(":feature:level:level11")
include(":feature:level:level12")
include(":feature:level:level13")
include(":feature:level:level14")
include(":feature:level:level15")
include(":feature:level:level16")
include(":feature:level:level17")
include(":feature:level:level18")
include(":feature:level:level19")
include(":feature:level:level20")
include(":feature:store")
include(":feature:settings")
include(":baselineprofile")
