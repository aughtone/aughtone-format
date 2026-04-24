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

group = libs.versions.namespace.get().toString()
version = libs.versions.versionName.get() // ${libs.versions.versionNameSiffix.get().toString()}

kotlin {
    jvmToolchain(17)

    jvm()
    android {
        namespace = "${libs.versions.namespace.get()}.toolbox"
        compileSdk {
            version = release(libs.versions.android.compileSdk.get().toInt())
        }
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputModuleName = "aughtone-format-toolbox"
                outputFileName = "aughtone-format-toolbox.js"
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
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    //noinspection WrongGradleMethod
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "FormatToolboxKit"
            isStatic = true
            binaryOption(
                "bundleId",
                "${libs.versions.namespace.get()}.toolbox"
            ) //"app.occurrence"
            binaryOption(
                "bundleShortVersionString",
                libs.versions.versionName.get().toString()
            ) //"1.0.0"
        }
    }

    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.startup.runtime)
            }
        }

        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.components.resources)
                api(libs.kotlinx.serialization.json)
                // XXX This might require additional libraries if you enable WASM or JS.
                //  See: https://klibs.io/project/Kotlin/kotlinx-datetime#using-in-your-projects
                api(libs.kotlinx.datetime)
                implementation(libs.aughtone.types)
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
    packageOfResClass = "${libs.versions.namespace.get()}.toolbox.resources"
    generateResClass = always
}


mavenPublishing {
    publishToMavenCentral()

    if (!project.hasProperty("skip-signing")) {
        signAllPublications()
    }

    coordinates(group.toString(), "format-toolbox", version.toString())

    pom {
        name = "Aughtone Format Multiplatform - Toolbox"
        description = "A Multiplatform library providing core utilities and primitives for the Aughtone ecosystem."
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
