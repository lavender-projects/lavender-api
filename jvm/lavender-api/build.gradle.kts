import de.honoka.gradle.buildsrc.MavenPublish.setupVersionAndPublishing

setupVersionAndPublishing(libs.versions.lavender.api.get())

dependencies {
    listOf(
        libs.jvm.honoka.kotlin.utils
    ).forEach {
        implementation(it)
        api(it)
    }
}