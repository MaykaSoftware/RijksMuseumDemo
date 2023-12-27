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

rootProject.name = "RijksMuseumDemo"
include(":app")
include(":feature:art:data")
include(":feature:art:domain")
include(":feature:art:ui")
include(":feature:art_details:data")
include(":feature:art_details:domain")
include(":feature:art_details:ui")
include(":core:network")
include(":core:common")
include(":core:feature_api")
include(":core:cache")
include(":feature:common:domain")
include(":feature:onboarding:ui")
include(":feature:bottombar")
