// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.kotlin.android") version "1.6.20-M1" apply false
    id("org.jmailen.kotlinter") version "3.9.0"
}

buildscript {
    dependencies {
        classpath(Google.dagger.hilt.android.gradlePlugin)
    }
}