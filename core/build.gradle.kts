import dependencies.Dependencies
import dependencies.AnnotationProcessorsDependencies
import extensions.implementation
import extensions.kapt
import extensions.getLocalProperty
import extensions.buildConfigBooleanField
import extensions.buildConfigIntField
import extensions.buildConfigStringField

plugins {
    id("commons.android-library")
}

allOpen {
    // allows mocking for classes w/o directly opening them for release builds
    annotation("com.android.mustafa.core.annotations.OpenClass")
}

android {
    buildTypes.forEach {
        try {
            it.buildConfigStringField("TMDB_API_BASE_URL", "https://api.themoviedb.org/")
            it.buildConfigStringField("TMDB_API_KEY", getLocalProperty("tmdb_api_key"))
            it.buildConfigBooleanField("CLEAN_ARCH_DATABASE_EXPORT_SCHEMA", false)
            it.buildConfigStringField("CLEAN_ARCH_DATABASE_NAME", "cleanarch-db")
            it.buildConfigIntField("CLEAN_ARCH_DATABASE_VERSION", 1)
        } catch (ignored: Exception) {
            throw InvalidUserDataException("You should define 'TMDB_API_KEY' in local.properties. Visit 'https://developers.themoviedb.org/3' " +
                    "to obtain it.")
        }
    }
}

dependencies {
    // Api instead of implementation
    //https://stackoverflow.com/questions/48121275/android-supertypes-of-the-following-classes-can-not-be-resolved-room-persis
    api(Dependencies.ROOM)
    api(Dependencies.ROOM_KTX)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER)
    implementation(Dependencies.LOGGING)
    implementation(Dependencies.MOSHI)
    implementation(Dependencies.MOSHI_KTX)
    implementation(Dependencies.COIL)

    kapt(AnnotationProcessorsDependencies.ROOM)
}
