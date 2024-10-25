plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.gms.google-services")
}

android {
    namespace = "za.co.varsitycollege.st10092141.vc_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "za.co.varsitycollege.st10092141.vc_app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    //QR scanner
    implementation (libs.zxing.android.embedded)
    implementation (libs.zxing.core)

    //Glide
    implementation (libs.glide)

    // ViewModel
    implementation (libs.androidx.lifecycle.lifecycle.viewmodel.ktx)  // Use the latest version

    // Other necessary AndroidX dependencies
    implementation (libs.androidx.fragment.ktx)

    implementation (libs.androidx.lifecycle.runtime.ktx)
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    // Coroutines for asynchronous operations
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    // Optional: OkHttp Logging Interceptor for debugging
    implementation(libs.logging.interceptor)
    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.recyclerview)

    // Import the BoM for the Firebase platform
    implementation(platform(libs.firebase.bom))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}