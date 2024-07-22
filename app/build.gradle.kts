plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    //for compose with kotlin 2.0.0+
    alias(libs.plugins.compose.compiler)

    alias( libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.jetapptech.halfwarenote"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jetapptech.halfwarenote"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //navigation
    implementation(libs.androidx.navigation.compose)


    //kotlinx serialisation
    implementation(libs.kotlinx.serialization.json)

    // https://mvnrepository.com/artifact/com.airbnb.android/lottie-compose
    implementation(libs.lottie.compose)

    // https://mvnrepository.com/artifact/androidx.paging/paging-runtime-ktx
    implementation(libs.androidx.paging.runtime.ktx)

    // https://mvnrepository.com/artifact/androidx.paging/paging-compose
    implementation(libs.androidx.paging.compose)




}