import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("gradle.plugin.com.github.johnrengelman:shadow:7.1.2")
    }
}


plugins {
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.compose") version "1.1.1"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.graalvm.buildtools.native") version "0.9.11"
}
apply(plugin = "com.github.johnrengelman.shadow")

group = "me.widi"
version = "1.0"

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    /*maven {
        url = uri("https://raw.githubusercontent.com/graalvm/native-build-tools/snapshots")
    }*/
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("com.google.code.gson:gson:2.8.5")
    implementation(kotlin("stdlib-jdk8"))
    implementation("androidx.compose:compose-runtime:0.1.0-dev10")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "rust_ai_dashboard"
            packageVersion = "1.1.0"
        }
    }
}
/*
tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "gui.Main"
    }
}*/

graalvmNative{
    binaries{
        named("main"){
            mainClass.set("MainKt")
            javaLauncher.set(javaToolchains.launcherFor{
                languageVersion.set(JavaLanguageVersion.of(11))
            })
        }
    }
}
/*
nativeBuild {
    javaLauncher.set(javaToolchains.launcherFor {
        languageVersion.set(JavaLanguageVersion.of(11))
        //vendor.set(JvmVendorSpec.matching("GraalVM Community"))
    })

}*/
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}