import de.honoka.gradle.buildsrc.MavenPublish.defineCheckVersionOfProjectsTask

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    `maven-publish`
    alias(libs.plugins.dependency.management)
}

allprojects {
    group = "de.honoka.lavender"
}

version = libs.versions.root.get()

subprojects {
    apply(plugin = "maven-publish")
    apply(plugin = "io.spring.dependency-management")

    publishing {
        repositories {
            mavenLocal()
        }
    }
}

defineCheckVersionOfProjectsTask()