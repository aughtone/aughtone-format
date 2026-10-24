import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
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
        namespace = "${libs.versions.namespace.get()}.format.astronomy"
        compileSdk {
            version = release(libs.versions.android.compileSdk.get().toInt())
        }
        minSdk {
            version = release(libs.versions.android.minSdk.get().toInt())
        }
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    js {
        browser()
        binaries.executable()
    }
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    //noinspection WrongGradleMethod
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "AOFormatAstronomyKit"
            isStatic = true
            binaryOption(
                "bundleId",
                "${libs.versions.namespace.get()}.format.astronomy"
            )
            binaryOption(
                "bundleShortVersionString",
                libs.versions.versionName.get().toString()
            )
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.kotlinx.datetime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
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

mavenPublishing {
    publishToMavenCentral(automaticRelease = true)

    val hasInMemoryKey = project.hasProperty("signingInMemoryKey") ||
            project.hasProperty("signingInMemoryKeyId") ||
            project.hasProperty("signing.gnupg.keyName")

    if (hasInMemoryKey && !project.hasProperty("skip-signing")) {
        signAllPublications()
    }

    coordinates(group.toString(), "format-astronomy", version.toString())

    pom {
        name = "Aughtone Format Multiplatform - Astronomy"
        description = "A Multiplatform library for sunrise, sunset, twilight, moon phases and solar terms, the same on every platform."
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
