import com.android.build.gradle.internal.api.DefaultAndroidSourceDirectorySet
import de.honoka.gradle.buildsrc.MavenPublish.defineAarSourcesJarTask

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.gradle.plugin)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "${project.group}.sdk.android"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()
}

subprojects {
    apply(plugin = "com.android.library")
    apply(plugin = "org.jetbrains.kotlin.android")

    android {
        val libs = rootProject.libs

        compileSdk = libs.versions.android.sdk.compile.get().toInt()

        defaultConfig {
            minSdk = libs.versions.android.sdk.min.get().toInt()
            testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
            consumerProguardFiles("consumer-rules.pro")
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                @Suppress("UnstableApiUsage")
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }
        }

        sourceSets["main"].java {
            val sourceDirSet = if(this is DefaultAndroidSourceDirectorySet) srcDirs else setOf()
            defineAarSourcesJarTask(sourceDirSet)
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = sourceCompatibility
        }

        kotlinOptions {
            jvmTarget = compileOptions.sourceCompatibility.toString()
        }
    }
}