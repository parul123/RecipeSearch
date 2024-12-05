plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    alias(libs.plugins.google.dagger.hilt)
}

android {
    namespace = "com.coding.exercise.receipesearch"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.coding.exercise.receipesearch"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    //Hilt
    implementation(libs.google.dagger.hilt)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.google.dagger.hilt.compiler)
    // Retrofit
    implementation (libs.retrofit2.retrofit)
    implementation (libs.retrofit2.converter.gson)
    //coil for image Loading
    implementation(libs.coil.compose)

    testImplementation(libs.junit)
    testImplementation ("org.mockito.kotlin:mockito-kotlin:4.1.0")
    testImplementation ("org.mockito:mockito-core:4.6.1")
    // Coroutine test library for testing coroutines
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.0")

    // To use assertEquals and other assertions in unit tests
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.7.1")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine:5.7.1")
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
kapt {
    correctErrorTypes = true
}