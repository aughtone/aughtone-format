import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import org.jetbrains.kotlin.gradle.tasks.KotlinCompileCommon

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.multiplatformLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.kotlinSerialization)
}

group = libs.versions.namespace.get()
version = libs.versions.versionName.get()

kotlin {
    jvmToolchain(17)

    jvm()
    android {
        namespace = "${libs.versions.namespace.get()}.format.viewable"
        compileSdk {
            version = release(libs.versions.android.compileSdk.get().toInt())
        }
        minSdk {
            version = release(libs.versions.android.minSdk.get().toInt())
        }
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputModuleName = "aughtone-format-viewable"
                outputFileName = "aughtone-format-viewable.js"
            }
        }
        binaries.executable()
    }

    js(IR) {
        browser {
            generateTypeScriptDefinitions()
        }
        useEsModules()
    }
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "AughtoneFormatViewableKit"
            isStatic = true
            binaryOption(
                "bundleId",
                "${libs.versions.namespace.get()}.format.viewable"
            )
            binaryOption(
                "bundleShortVersionString",
                libs.versions.versionName.get()
            )
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":toolbox"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }

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

mavenPublishing {
    publishToMavenCentral(automaticRelease = true)

    if (!project.hasProperty("skip-signing")) {
        signAllPublications()
    }

    coordinates(group.toString(), "format-viewable", version.toString())

    pom {
        name = "Aughtone Format Multiplatform - Viewable"
        description = "A Multiplatform library for abstract visual models and rendering instructions."
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
