plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.9.21"
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.feature.common.domain"
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

    testImplementation(Deps.junit)
    androidTestImplementation(Deps.junitExt)


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

    //Room
    implementation(Deps.roomRuntime)
    ksp(Deps.roomCompiler)
    implementation(Deps.roomPaging)
    //Kotlin extensions
    implementation(Deps.roomKtx)

    implementation(Deps.kotlinxSerialization)
}