plugins {
    alias(libs.plugins.multiplatformLibrary) apply false
    alias(libs.plugins.kotlinMultiplatform) apply  false
    alias(libs.plugins.vanniktech.mavenPublish) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
}
