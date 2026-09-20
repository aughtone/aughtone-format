import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import org.jetbrains.kotlin.gradle.tasks.KotlinCompileCommon

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.multiplatformLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = libs.versions.namespace.get()
version = libs.versions.versionName.get()

kotlin {
    jvmToolchain(17)

    jvm()
    android {
        namespace = "${libs.versions.namespace.get()}.format.identifiers"
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
                outputModuleName = "aughtone-format-identifiers"
                outputFileName = "aughtone-format-identifiers.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
    }

    // See: https://kotlinlang.org/docs/js-project-setup.html
    js(IR) {
        browser {
            generateTypeScriptDefinitions()
        }
        useEsModules()
    }
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    //noinspection WrongGradleMethod
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "AughtoneFormatIdentifiersKit"
            isStatic = true
            binaryOption("bundleId", "${libs.versions.namespace.get()}.format.identifiers")
            binaryOption("bundleShortVersionString", libs.versions.versionName.get().toString())
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // The module's only dependency: the phone formatter needs
                // aughtone-phonenumber's per-country metadata. Every other
                // formatter is a pure, table-free transform. The metadata is
                // dead-code-eliminated for consumers that never format a phone (#8).
                implementation(libs.aughtone.phonenumber)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }

    // Workaround: https://youtrack.jetbrains.com/issue/KT-66568
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

    coordinates(group.toString(), "format-identifiers", version.toString())

    pom {
        name = "Aughtone Format Multiplatform - Identifiers"
        description = "Display formatters for canonical identifiers (MAC, UUID, IBAN, IP, domain, phone) in the Aughtone ecosystem."
        inceptionYear = "2026"
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
                name = "bpappin"
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
