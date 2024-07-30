plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    //for compose with kotlin 2.0.0+
    alias(libs.plugins.compose.compiler)

    alias( libs.plugins.kotlin.serialization)


    alias(libs.plugins.ksp)

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

    //lottie
    // https://mvnrepository.com/artifact/com.airbnb.android/lottie-compose
    implementation(libs.lottie.compose)



    //view model
    // https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-viewmodel-compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)


    //dataStore preferences
    implementation(libs.androidx.datastore.preferences)


    //splash screen
    implementation(libs.androidx.core.splashscreen)


    //constraint layout
    // https://mvnrepository.com/artifact/androidx.constraintlayout/constraintlayout-compose
    implementation(libs.androidx.constraintlayout.compose)


    //coil
    // https://mvnrepository.com/artifact/io.coil-kt/coil-compose
    implementation(libs.coil.compose)


    //color picker
    implementation(libs.colorpicker.compose)






    // https://mvnrepository.com/artifact/androidx.paging/paging-compose
    implementation(libs.androidx.paging.compose)

    // https://mvnrepository.com/artifact/androidx.paging/paging-runtime-ktx
    implementation(libs.androidx.paging.runtime.ktx)


    // optional - Paging 3 Integration
    implementation(libs.androidx.room.paging)



    //room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    // To use Kotlin Symbol Processing (KSP)
    ksp(libs.androidx.room.compiler)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)



    //koin
    //koin-android
    implementation(libs.koin.android)
    //koin-androidx-navigation
    implementation(libs.koin.androidx.navigation)
    //koin-androidx-compose
    implementation(libs.koin.androidx.compose)


}