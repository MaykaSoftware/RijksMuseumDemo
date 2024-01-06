plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
}
android {
    namespace = "com.feature.home.domain"
}



dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.junit.ext)
    androidTestImplementation(libs.androidx.espresso)
}