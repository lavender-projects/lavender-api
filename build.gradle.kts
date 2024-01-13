import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    `maven-publish`
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
}

group = "de.honoka.lavender"
version = "1.0.0-dev"

allprojects {
    java {
        withSourcesJar()
        withJavadocJar()
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    dependencies {
        
        //Test
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    }

    tasks {
        compileJava {
            options.encoding = "UTF-8"
        }

        withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "1.8"
        }

        test {
            useJUnitPlatform()
        }
    }

    publishing {
        repositories {
            mavenLocal()
            if(hasProperty("remoteMavenRepositoryUrl")) {
                maven(properties["remoteMavenRepositoryUrl"]!!)
            }
        }
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    group = rootProject.group
}