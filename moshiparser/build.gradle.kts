plugins {
    id("com.android.library")
    id("kotlin-android")
    id("maven-publish")
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        // No dependencies needed here (unless you use buildscript blocks)
    }
}

android {
    namespace = "work.upstarts.moshiparser"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    publishing {
        multipleVariants {
            allVariants()
            withJavadocJar()
            withSourcesJar()
        }
    }
}

dependencies {
    api(project(":editorjskit"))
    implementation(libs.moshi)
    implementation(libs.moshi.adapters)

    testImplementation(libs.junit)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("mavenRelease") {
                groupId = "io.github.upstarts"
                artifactId = "ejkit-moshi"
                version = libs.versions.publishVersion.get()

                from(components["release"])
            }
        }
    }
}
