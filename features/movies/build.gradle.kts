import dependencies.Dependencies
import extensions.implementation

plugins {
    id("commons.android-dynamic-feature")
}

dependencies {
//    implementation(project(BuildModules.Features.MOVIES))

    implementation(Dependencies.RECYCLE_VIEW)
}
