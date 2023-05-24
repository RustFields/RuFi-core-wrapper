plugins {
    id("java")
    alias(libs.plugins.taskTree)
    alias(libs.plugins.spotBugs)
    checkstyle
    pmd
}

group = "io.github.rustfields"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
