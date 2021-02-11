package commons

import BuildAndroidConfig
import BuildModules
import extensions.addTestsDependencies
import BuildProductDimensions
import ProductFlavorDevelop
import ProductFlavorProduction
import ProductFlavorQA
import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import extensions.implementation

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-allopen")
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = true
    }

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
//    implementation(Dependencies.KOTLIN)
//    implementation(Dependencies.KOTLIN_REFLECT)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.DAGGER)
    implementation(Dependencies.TIMBER)

    kapt(AnnotationProcessorsDependencies.DAGGER)

//    testImplementation(project(BuildModules.Libraries.TEST_UTILS))
    addTestsDependencies()
}
