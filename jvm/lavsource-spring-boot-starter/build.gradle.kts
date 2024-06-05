import de.honoka.gradle.buildsrc.MavenPublish.setupVersionAndPublishing

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.spring.plugin)
}

setupVersionAndPublishing(libs.versions.lavsource.spring.boot.starter.get())

dependencyManagement {
    imports {
        mavenBom(libs.spring.boot.dependencies.get().toString())
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-configuration-processor".also {
        annotationProcessor(it)
    })
    implementation(libs.jvm.lavender.api.also {
        api(it)
    })
    implementation(libs.jvm.honoka.framework.utils)
    implementation("org.hibernate.validator:hibernate-validator")
}

tasks {
    compileJava {
        dependsOn(":jvm:lavender-api:publish")
    }
}