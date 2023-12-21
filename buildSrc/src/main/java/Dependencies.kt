object Version {
    const val core = "1.12.0"
    const val lifecycle = "2.6.2"
    const val activityCompose = "1.8.2"
    const val composeBom = "2023.10.00"
    const val material3 = "1.1.2"

    const val composeViewModel = "2.6.2"
    const val materialIcons = "1.5.4"

    const val hilt = "2.48.1"
    const val hiltNavigation = "1.1.0"

    const val room = "2.6.1"

    const val kotlinxSerialization = "1.6.0"

    const val coil = "2.4.0"

    //Paging
    const val paging = "3.2.1"

    //Retrofit
    const val retrofit = "2.9.0"
    const val kotlinConverter = "1.0.0"
    const val loggingInterceptor = "4.11.0"

    //Navigation
    const val composeNavigation = "2.7.6"

    const val junit = "4.13.2"
    const val testCore = "1.5.0"
    const val archCoreTesting = "2.2.0"
    const val kotlinCoroutinesTest = "1.7.3"
    const val truth = "1.1.5"
    const val mockk = "1.13.7"
    const val turbine = "1.0.0"

    const val junitExt = "1.1.5"
    const val testRunner = "1.5.2"
    const val testRules = "1.5.0"
    const val composeBomTest = "2023.10.00"
}

object Deps {

    const val junit = "junit:junit:${Version.junit}"
    const val testCore = "androidx.test:core:${Version.testCore}"
    const val archCoreTesting = "androidx.arch.core:core-testing:${Version.archCoreTesting}"
    const val kotlinCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.kotlinCoroutinesTest}"
    const val truth = "com.google.truth:truth:${Version.truth}"
    const val mockk = "io.mockk:mockk:${Version.mockk}"
    const val turbine = "app.cash.turbine:turbine:${Version.turbine}"


    const val junitExt = "androidx.test.ext:junit:${Version.junitExt}"
    const val testRunner = "androidx.test:runner:${Version.testRunner}"
    const val testRules = "androidx.test:rules:${Version.testRules}"
    const val composeBomTest = "androidx.compose:compose-bom:${Version.composeBomTest}"
    const val uiTest = "androidx.compose.ui:ui-test-junit4"
    const val composeToolingTest = "androidx.compose.ui:ui-tooling"
    const val composeManifestTest = "androidx.compose.ui:ui-test-manifest"

    const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Version.hilt}"
    const val hiltAndroidCompilerTesting = "com.google.dagger:hilt-android-compiler:${Version.hilt}"

    //compose
    const val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.composeViewModel}"
    const val materialIcons = "androidx.compose.material:material-icons-extended:${Version.materialIcons}"

    const val core = "androidx.core:core-ktx:${Version.core}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
    const val activityCompose = "androidx.activity:activity-compose:${Version.activityCompose}"
    const val composeBom = "androidx.compose:compose-bom:${Version.composeBom}"
    const val composeUI = "androidx.compose.ui:ui"
    const val composeGraphics = "androidx.compose.ui:ui-graphics"
    const val composeTooling = "androidx.compose.ui:ui-tooling-preview"
    const val material3 = "androidx.compose.material3:material3:${Version.material3}"

    //Hilt
    const val hiltAndroid = "com.google.dagger:hilt-android:${Version.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Version.hiltNavigation}"

    //Room
    const val roomRuntime = "androidx.room:room-runtime:${Version.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.room}"
    const val roomPaging = "androidx.room:room-paging:${Version.room}"
    //Kotlin extensions
    const val roomKtx = "androidx.room:room-ktx:${Version.room}"

    const val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.kotlinxSerialization}"

    //Coil compose
    const val coil = "io.coil-kt:coil-compose:${Version.coil}"

    //Paging
    const val pagingRuntime = "androidx.paging:paging-runtime-ktx:${Version.paging}"
    const val pagingCompose = "androidx.paging:paging-compose:${Version.paging}"

    //Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val retrofitConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Version.kotlinConverter}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.loggingInterceptor}"

    //Navigation
    const val composeNavigation = "androidx.navigation:navigation-compose:${Version.composeNavigation}"
}

object DaggerHilt {

}