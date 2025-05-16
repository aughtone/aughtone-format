import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import org.jetbrains.kotlin.gradle.tasks.KotlinCompileCommon

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

group = "io.github.aughtone"
version = "${libs.versions.versionName.get().toString()}" // ${libs.versions.versionNameSiffix.get().toString()}

kotlin {
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputModuleName = "aughtone-format-datetime"
                outputFileName = "aughtone-format-datetime.js"
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

    // See: https://kotlinlang.org/docs/js-project-setup.html
    js(IR) {
        browser {
            generateTypeScriptDefinitions()
        }
        useEsModules() // Enables ES2015 modules
//        binaries.executable()
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    //noinspection WrongGradleMethod
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "FormatDatetimeKit"
            isStatic = true
            binaryOption(
                "bundleId",
                libs.versions.applicationId.get().toString()
            ) //"app.occurrence"
            binaryOption(
                "bundleShortVersionString",
                libs.versions.versionName.get().toString()
            ) //"1.0.0"
        }
    }
//    linuxX64()

    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.startup.runtime)
            }
        }
        val androidInstrumentedTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.kotlin.test.junit)
                implementation(libs.androidx.runner)
                implementation(libs.androidx.rules)
            }
        }

        val commonMain by getting {
            dependencies {
//                implementation(compose.runtime)
//                implementation(compose.foundation)
//                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                api(libs.kotlinx.serialization.json)
                // XXX This might require additional libraries if you enable WASM or JS.
                //  See: https://klibs.io/project/Kotlin/kotlinx-datetime#using-in-your-projects
                api(libs.kotlinx.datetime)
                implementation(libs.jacobras.human.readable)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                api(libs.kotlinx.serialization.json)
            }
        }
    }

    compilerOptions {
        freeCompilerArgs.add("-Xmulti-dollar-interpolation")

        // XXX Activate when this is resolved:
        //  https://youtrack.jetbrains.com/issue/KT-57847/Move-common-for-all-the-backends-module-name-compiler-option-to-the-KotlinCommonCompilerOptions
        // moduleName = "io.github.aughtone.datetime.format"
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
    packageOfResClass = "${libs.versions.applicationId.get().toString()}.resources"
    generateResClass = always
}

android {
    namespace = libs.versions.applicationId.get().toString()
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    if (!project.hasProperty("skip-signing")) {
        signAllPublications()
    }

    coordinates(group.toString(), "format-datetime", version.toString())

    pom {
        name = "Aughtone Format Multiplatform - Datetime"
        description = "A library."
        inceptionYear = "2025"
        url = "https://github.com/aughtone/aughtone-format"
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
            url = "https://github.com/aughtone/aughtone-format"
            connection = "https://github.com/aughtone/aughtone-format.git"
            developerConnection = "git@github.com:aughtone/aughtone-format.git"
        }
    }
}
