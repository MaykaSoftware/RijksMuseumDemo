plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.9.21"
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.farid.rijksmuseumdemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.farid.rijksmuseumdemo"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.6"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:feature_api"))
    implementation(project(":feature:bottombar"))

    implementation(project(":feature:onboarding:data"))
    implementation(project(":feature:onboarding:domain"))
    implementation(project(":feature:onboarding:ui"))

    implementation(project(":feature:authentication:data"))
    implementation(project(":feature:authentication:domain"))
    implementation(project(":feature:authentication:ui"))

    implementation(project(":feature:home:data"))
    implementation(project(":feature:home:domain"))
    implementation(project(":feature:home:ui"))

    implementation(project(":feature:art:data"))
    implementation(project(":feature:art:domain"))
    implementation(project(":feature:art:ui"))

    implementation(project(":feature:settings:data"))
    implementation(project(":feature:settings:domain"))
    implementation(project(":feature:settings:ui"))

    implementation(Deps.core)
    implementation(Deps.lifecycle)
    implementation(Deps.activityCompose)
    implementation(Deps.composeBom)
    implementation(Deps.composeUI)
    implementation(Deps.composeGraphics)
    implementation(Deps.composeTooling)
    implementation(Deps.material3)


    testImplementation(Deps.junit)
    testImplementation(Deps.testCore)
    testImplementation(Deps.archCoreTesting)
    testImplementation(Deps.kotlinCoroutinesTest)
    testImplementation(Deps.truth)
    testImplementation(Deps.mockk)
    testImplementation(Deps.turbine)
    implementation(Deps.uiTest)


    androidTestImplementation(Deps.junitExt)
    androidTestImplementation(Deps.testRunner)
    androidTestImplementation(Deps.testRules)
    androidTestImplementation(Deps.composeBomTest)
    androidTestImplementation(Deps.uiTest)
    debugImplementation(Deps.composeToolingTest)
    debugImplementation(Deps.composeManifestTest)

    androidTestImplementation(Deps.hiltAndroidTesting)
    kaptAndroidTest(Deps.hiltAndroidCompilerTesting)

    //compose
    implementation(Deps.composeViewModel)
    implementation(Deps.materialIcons)

    //Hilt
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)
    implementation(Deps.hiltNavigation)

    //Room
    implementation(Deps.roomRuntime)
    ksp(Deps.roomCompiler)
    implementation(Deps.roomPaging)
    //Kotlin extensions
    implementation(Deps.roomKtx)

    implementation(Deps.kotlinxSerialization)

    //Coil compose
    implementation(Deps.coil)

    //Paging
    implementation(Deps.pagingRuntime)
    implementation(Deps.pagingCompose)

    //Retrofit
    implementation(Deps.retrofit)
    implementation(Deps.retrofitConverter)
    implementation(Deps.loggingInterceptor)

    //Navigation
    implementation(Deps.composeNavigation)

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}