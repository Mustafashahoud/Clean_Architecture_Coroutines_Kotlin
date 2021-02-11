package commons

import BuildAndroidConfig
import BuildModules
import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import extensions.addTestsDependencies
import extensions.implementation

plugins {
    id("com.android.dynamic-feature")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-allopen")
    id("androidx.navigation.safeargs.kotlin")
}

allOpen {
    // allows mocking for classes w/o directly opening them for release builds
    annotation("com.android.mustafa.core.annotations.OpenClass")
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)

        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    dataBinding {
        @Suppress("DEPRECATION")
        isEnabled = true
    }


//    buildFeatures {
//        dataBinding = true
//    }

//    flavorDimensions(BuildProductDimensions.ENVIRONMENT)
//    productFlavors {
//        ProductFlavorDevelop.libraryCreate(this)
//        ProductFlavorQA.libraryCreate(this)
//        ProductFlavorProduction.libraryCreate(this)
//    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }

    lintOptions {
        lintConfig = rootProject.file(".lint/config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}

//junitJacoco {
//    includeNoLocationClasses = true
//}

dependencies {
    implementation(project(BuildModules.APP))
    implementation(project(BuildModules.CORE))
    implementation(project(BuildModules.Commons.UI))

//    implementation(Dependencies.KOTLIN)
//    implementation(Dependencies.KOTLIN_REFLECT)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    implementation(Dependencies.LIFECYCLE_LIVEDATA)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    implementation(Dependencies.DAGGER)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.LOGGING)

    kapt(AnnotationProcessorsDependencies.DAGGER)
    kapt(AnnotationProcessorsDependencies.ROOM)

//    testImplementation(project(BuildModules.Libraries.TEST_UTILS))
    addTestsDependencies()
}
