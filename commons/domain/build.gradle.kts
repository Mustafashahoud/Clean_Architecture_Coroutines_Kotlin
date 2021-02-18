import dependencies.Dependencies
import dependencies.TestDependencies
import extensions.implementation

plugins {
    id("commons.android-library")
}

//junitJacoco {
//    excludes = listOf("**/extensions/NavigationExtensions*.*")
//}

dependencies {

    implementation(TestDependencies.COROUTINES_TEST)
    implementation(Dependencies.RETROFIT)

}
