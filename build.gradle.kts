// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}

allprojects {
    plugins.withId("org.gradle.maven-publish") {
        group = "io.github.upstarts"
        version = libs.versions.publishVersion.get()
    }
}
