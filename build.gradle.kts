// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    //compose with kotlin 2.0.0+
    alias(libs.plugins.compose.compiler) apply false

    //using ksp
    alias(libs.plugins.ksp)

}