@file:Suppress("UnstableApiUsage")

dependencyResolutionManagement {
    repositories {
        maven("https://maven.aliyun.com/repository/public")
        mavenCentral()
        mavenLocal()
        maven("https://mirrors.honoka.de/maven-repo")
    }
}

pluginManagement {
    repositories {
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "lavender-sdk"

include("lavender-api")
include("lavsource-spring-boot-starter")