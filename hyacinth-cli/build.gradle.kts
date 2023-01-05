plugins {
    application
}

application {
    mainClass.set("com.github.xini1.hyacinth.Main")
}

dependencies {
    implementation(libs.gson)
    implementation(project(":hyacinth-core"))

    testCompileOnly(libs.junit.api)
    testRuntimeOnly(libs.junit.engine)
    testImplementation(libs.assertj)
}

tasks.test {
    useJUnitPlatform()
}