plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.9.21"
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.feature.art.domain"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":feature:common:domain"))
    implementation(project(":feature:art:data"))

    testImplementation(Deps.junit)
    testImplementation (Deps.testCore)
    testImplementation (Deps.archCoreTesting)
    testImplementation (Deps.kotlinCoroutinesTest)
    testImplementation (Deps.truth)
    testImplementation (Deps.mockk)
    testImplementation (Deps.turbine)


    androidTestImplementation(Deps.junitExt)
    androidTestImplementation (Deps.testRunner)
    androidTestImplementation (Deps.testRules)
    androidTestImplementation(Deps.composeBomTest)
    androidTestImplementation(Deps.uiTest)
    debugImplementation(Deps.composeToolingTest)
    debugImplementation(Deps.composeManifestTest)

    androidTestImplementation(Deps.hiltAndroidTesting)
    kaptAndroidTest(Deps.hiltAndroidCompilerTesting)

    implementation(Deps.core)
    implementation(Deps.lifecycle)
    implementation(Deps.activityCompose)
    implementation(Deps.composeBom)
    implementation(Deps.composeUI)
    implementation(Deps.composeGraphics)
    implementation(Deps.composeTooling)
    implementation(Deps.material3)

    //Paging
    implementation(Deps.pagingRuntime)
    implementation(Deps.pagingCompose)

    //Hilt
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)
    implementation(Deps.hiltNavigation)

    //Retrofit
    implementation (Deps.retrofit)
    implementation (Deps.retrofitConverter)
    implementation (Deps.loggingInterceptor)
}