plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = Version.compileSdk

    defaultConfig {
        applicationId = "com.hsicen.animation"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
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

    testImplementation(Deps.testLib)
    androidTestImplementation(Deps.androidTestLib)
    debugImplementation(Deps.debugLib)
}