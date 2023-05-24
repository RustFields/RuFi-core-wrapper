plugins {
    id("java")
    alias(libs.plugins.taskTree)
    alias(libs.plugins.spotBugs)
    checkstyle
    pmd
    alias(libs.plugins.publishCentral)
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

publishOnCentral {
    projectUrl.set("https://github.com/RustFields/${project.name}")
    scmConnection.set("git:git@github.com:RustFields/${project.name}")
}

publishing {
    publications {
        withType<MavenPublication> {
            pom {
                developers {
                    developer {
                        name.set("Filippo Vissani")
                        email.set("filippo.vissani@studio.unibo.it")
                        url.set("https://filippovissani.github.io/")
                    }
                    developer {
                        name.set("Leonardo Micelli")
                        email.set("leonardo.micelli@studio.unibo.it")
                        url.set("https://lm98.github.io/")
                    }
                    developer {
                        name.set("Paolo Penazzi")
                        email.set("paolo.penazzi@studio.unibo.it")
                        url.set("https://paolopenazzi.github.io/")
                    }
                    developer {
                        name.set("Angela Cortecchia")
                        email.set("angela.cortecchia@studio.unibo.it")
                        url.set("https://angelacorte.github.io/")
                    }
                }
            }
        }
    }
}

if(System.getenv("CI") == true.toString()){
    signing {
        val signingKey: String? by project
        val signingPassword: String? by project
        useInMemoryPgpKeys(signingKey, signingPassword)
    }
}
