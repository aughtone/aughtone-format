plugins {
    alias(libs.plugins.multiplatformLibrary) apply false
    alias(libs.plugins.kotlinMultiplatform) apply  false
    alias(libs.plugins.vanniktech.mavenPublish) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
}

subprojects {
    configurations.all {
        resolutionStrategy.eachDependency {
            val composeVersion = libs.versions.compose.multiplatform.get()
            val lifecycleVersion = libs.versions.jetbrains.lifecycle.get()
            val savedstateVersion = libs.versions.jetbrains.savedstate.get()

            when (requested.group) {
                "androidx.lifecycle" -> {
                    useTarget("org.jetbrains.androidx.lifecycle:${requested.name}:$lifecycleVersion")
                }
                "androidx.savedstate" -> {
                    useTarget("org.jetbrains.androidx.savedstate:${requested.name}:$savedstateVersion")
                }
                "androidx.annotation" -> {
                    if (requested.name == "annotation") {
                        useTarget("org.jetbrains.compose.annotation-internal:annotation:$composeVersion")
                    }
                }
                "androidx.collection" -> {
                    if (requested.name == "collection") {
                        useTarget("org.jetbrains.compose.collection-internal:collection:$composeVersion")
                    }
                }
            }

            if (requested.group == "androidx.compose.runtime") {
                if (requested.name == "runtime" || requested.name == "runtime-saveable") {
                    useTarget("org.jetbrains.compose.runtime:${requested.name}:$composeVersion")
                }
            }
        }
    }
}
