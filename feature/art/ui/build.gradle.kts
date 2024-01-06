plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.feature.art.ui"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.core.featureApi)
    implementation(projects.core.common)
    implementation(projects.feature.art.domain)

    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    testImplementation(libs.androidx.junit)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.archCoreTesting)
    testImplementation(libs.jetbrains.kotlinCoroutinesTest)
    testImplementation(libs.google.truth)
    testImplementation(libs.io.mockk)
    testImplementation(libs.cash.turbine)


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

    //Navigation
    implementation(libs.navigation.compose)

    //Paging
    implementation(libs.paging.runtime.ktx)
    implementation(libs.paging.compose)

    //Coil compose
    implementation(libs.coil.compose)
}