plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = Version.compileSdk

    defaultConfig {
        applicationId = "com.hsicen.navigation"
        minSdk = Version.minSdk
        targetSdk = Version.targetSdk
        versionCode = Version.versionCode
        versionName = Version.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Version.compose
    }
}

dependencies {
    implementation(Deps.appLib)
    implementation(Deps.composeLib)
    implementation(Deps.lifecycleLib)

    implementation(Deps.navigationCompose)

    testImplementation(Deps.testLib)
    androidTestImplementation(Deps.androidTestLib)
    debugImplementation(Deps.debugLib)
}