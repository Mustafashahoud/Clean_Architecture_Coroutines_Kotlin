plugins {
    id("commons.android-dynamic-feature")
}

dependencies {
    implementation(project(BuildModules.Commons.DOMAIN))
    implementation(project(BuildModules.Commons.UI))
}
