plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
    mavenCentral()
    maven ("https://jitpack.io")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://plugins.gradle.org/m2/")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

object PluginsVersions {
    const val GRADLE_ANDROID = "4.1.2"
    const val GRADLE_VERSIONS = "0.36.0"
    const val KOTLIN = "1.4.30"
    const val NAVIGATION = "2.3.0"
    const val JACOCO = "0.16.0"
    const val DOKKA = "0.10.0"
    const val KTLINT = "0.39.0"
    const val SPOTLESS = "5.6.1"
    const val DETEKT = "1.14.1"
    const val GRAPH_GENERATOR = "0.6.0-SNAPSHOT"
}


dependencies {
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    implementation("com.github.ben-manes:gradle-versions-plugin:${PluginsVersions.GRADLE_VERSIONS}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")
    implementation("org.jetbrains.kotlin:kotlin-allopen:${PluginsVersions.KOTLIN}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION}")
//    implementation("com.vanniktech:gradle-android-junit-jacoco-plugin:${PluginsVersions.JACOCO}")
//    implementation("com.vanniktech:gradle-dependency-graph-generator-plugin:${PluginsVersions.GRAPH_GENERATOR}")
//    implementation("org.jetbrains.dokka:dokka-gradle-plugin:${PluginsVersions.DOKKA}")
//    implementation("com.pinterest:ktlint:${PluginsVersions.KTLINT}")
//    implementation("com.diffplug.spotless:spotless-plugin-gradle:${PluginsVersions.SPOTLESS}")
//    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${PluginsVersions.DETEKT}")
}


