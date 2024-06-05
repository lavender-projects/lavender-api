import de.honoka.gradle.buildsrc.kotlin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.nio.charset.StandardCharsets

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    java
    alias(libs.plugins.jvm.kotlin) apply false
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = sourceCompatibility
        withSourcesJar()
    }

    //noinspection UseTomlInstead
    dependencies {
        kotlin(rootProject)
        implementation("cn.hutool:hutool-all:5.8.18")
        //Test
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    }

    tasks {
        compileJava {
            options.encoding = StandardCharsets.UTF_8.name()
        }

        withType<KotlinCompile> {
            kotlinOptions.jvmTarget = java.sourceCompatibility.toString()
        }

        test {
            useJUnitPlatform()
        }
    }
}