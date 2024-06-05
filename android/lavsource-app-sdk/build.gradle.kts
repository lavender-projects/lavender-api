import de.honoka.gradle.buildsrc.MavenPublish.setupVersionAndPublishing

setupVersionAndPublishing(libs.versions.lavsource.app.sdk.get(), true)

android {
    namespace = "${project.group}.sdk.android.lavsource"
}

dependencies {
    listOf(
        libs.android.lavender.api,
        libs.honoka.android.utils
    ).forEach {
        implementation(it)
        api(it)
    }
    implementation(libs.android.honoka.framework.utils)
    implementation("cn.hutool:hutool-all:5.8.18")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

tasks {
    preBuild {
        dependsOn(":jvm:lavender-api:publish")
    }
}