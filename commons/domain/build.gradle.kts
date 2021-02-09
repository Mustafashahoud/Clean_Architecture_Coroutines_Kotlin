import dependencies.Dependencies
import extensions.implementation

plugins {
    id("commons.android-library")
}

junitJacoco {
    excludes = listOf("**/extensions/NavigationExtensions*.*")
}

dependencies {


}
