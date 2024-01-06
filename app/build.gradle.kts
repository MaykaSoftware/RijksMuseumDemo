plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.farid.rijksmuseumdemo"

    defaultConfig {
        applicationId = "com.farid.rijksmuseumdemo"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.featureApi)
    implementation(projects.feature.bottombar)
    implementation(projects.feature.topbar)

    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(projects.feature.onboarding.data)
    implementation(projects.feature.onboarding.domain)
    implementation(projects.feature.onboarding.ui)

    implementation(projects.feature.authentication.data)
    implementation(projects.feature.authentication.domain)
    implementation(projects.feature.authentication.ui)

    implementation(projects.feature.home.data)
    implementation(projects.feature.home.domain)
    implementation(projects.feature.home.ui)

    implementation(projects.feature.art.data)
    implementation(projects.feature.art.domain)
    implementation(projects.feature.art.ui)

    implementation(projects.feature.settings.data)
    implementation(projects.feature.settings.domain)
    implementation(projects.feature.settings.ui)

    testImplementation(libs.androidx.junit)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.archCoreTesting)
    testImplementation(libs.jetbrains.kotlinCoroutinesTest)
    testImplementation(libs.google.truth)
    testImplementation(libs.io.mockk)
    testImplementation(libs.cash.turbine)
    implementation(libs.androidx.compose.test.junit4)

    androidTestImplementation(libs.androidx.junit.ext)
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.rules)
    androidTestImplementation(libs.androidx.compose.test.junit4)
    debugImplementation(libs.androidx.compose.tooling)
    debugImplementation(libs.androidx.compose.test.manifest)

    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.lifecycle.runtime.ktx )
    implementation(libs.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.graphics)
    implementation(libs.androidx.compose.tooling.preview)
    implementation(libs.androidx.compose.material3)

    //compose
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.material.icons.extended)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    //Room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.paging)
    //Kotlin extensions
    implementation(libs.room.ktx)

    implementation(libs.kotlinx.serialization.json)

    //Coil compose
    implementation(libs.coil.compose)

    //Paging
    implementation(libs.paging.runtime.ktx)
    implementation(libs.paging.compose)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp3.logging.interceptor)

    //Navigation
    implementation(libs.navigation.compose)

    //splash
    implementation(libs.splashscreen)

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}