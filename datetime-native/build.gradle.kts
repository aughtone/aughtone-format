import org.gradle.kotlin.dsl.assign
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import org.jetbrains.kotlin.gradle.tasks.KotlinCompileCommon

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.multiplatformLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

group = "io.github.aughtone"
version = libs.versions.versionName.get()

kotlin {
    jvmToolchain(17)
    jvm()
//    androidTarget {
//        publishLibraryVariants("release")
//        @OptIn(ExperimentalKotlinGradlePluginApi::class)
//        compilerOptions {
//            jvmTarget.set(JvmTarget.JVM_17)
//        }
//    }
    android {
        namespace = "io.github.aughtone.datetime.format.native"
        compileSdk {
            version = release(libs.versions.android.compileSdk.get().toInt())
        }
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        outputModuleName = "datetime-native"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "datetime-native.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
//    linuxX64()

    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.startup.runtime)
            }
        }
//        val androidInstrumentedTest by getting {
//            dependencies {
//                implementation(libs.kotlin.test)
//                implementation(libs.kotlin.test.junit)
//                implementation(libs.androidx.runner)
//                implementation(libs.androidx.rules)
//            }
//        }

        val commonMain by getting {
            dependencies {
//                implementation(compose.runtime)
//                implementation(compose.foundation)
//                implementation(compose.material3)
                implementation(libs.jetbrains.compose.ui)
                implementation(libs.jetbrains.compose.resources)
                api(libs.kotlinx.serialization.json)
                // XXX This might require additional libraries if you enable WASM or JS.
                //  See: https://klibs.io/project/Kotlin/kotlinx-datetime#using-in-your-projects
                api(libs.kotlinx.datetime)
//                api(libs.datetime.format)
//                api(projects.datetimeFormat)
//                api(libs.kotlinx.coroutines.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }

    compilerOptions {
        freeCompilerArgs.add("-Xmulti-dollar-interpolation")

        // XXX Activate when this is resolved:
        //  https://youtrack.jetbrains.com/issue/KT-57847/Move-common-for-all-the-backends-module-name-compiler-option-to-the-KotlinCommonCompilerOptions
        // moduleName = "io.github.aughtone.datetime.format.native"
    }

    // XXX Remove when the above is resolved. This is a workaround.
    //  https://youtrack.jetbrains.com/issue/KT-66568/w-KLIB-resolver-The-same-uniquename...-found-in-more-than-one-library
    metadata {
        compilations.all {
            val compilationName = rootProject.name
            compileTaskProvider.configure {
                if (this is KotlinCompileCommon) {
                    moduleName = "${project.group}:${project.name}_$compilationName"
                }
            }
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "io.github.aughtone.datetime.format.native.resources"
    generateResClass = always
}


mavenPublishing {
//    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    publishToMavenCentral()

    if (!project.hasProperty("skip-signing")) {
        signAllPublications()
    }

    coordinates(group.toString(), "format-datetime-native", version.toString())

    pom {
        name = "DateTime Format Multiplatform"
        description = "A library."
        inceptionYear = "2025"
        url = "https://github.com/aughtone/datetime-format"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "bpappin"
                name = "Brill pappin"
                url = "https://github.com/bpappin"
            }
        }
        scm {
            url = "https://github.com/aughtone/datetime-format"
            connection = "https://github.com/aughtone/datetime-format.git"
            developerConnection = "git@github.com:aughtone/datetime-format.git"
        }
    }
}
