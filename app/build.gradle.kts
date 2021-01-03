plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdkVersion(Configuration.compileSdk)
    buildToolsVersion(Configuration.buildTools)

    defaultConfig {
        applicationId = "de.datlag.coilifierandroid"
        minSdkVersion(Configuration.minSdk)
        targetSdkVersion(Configuration.targetSdk)
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            isShrinkResources = false
            isZipAlignEnabled = false
            isJniDebuggable = true
            isRenderscriptDebuggable = true
        }

        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            isZipAlignEnabled = true
            isJniDebuggable = false
            isRenderscriptDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = CompileOptions.sourceCompatibility
        targetCompatibility = CompileOptions.targetCompatibility
    }

    kotlinOptions {
        jvmTarget = CompileOptions.jvmTarget
    }
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib${CompileOptions.kotlinJdk}:${Versions.kotlin}")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation(project(":coilifier"))
    testImplementation("junit:junit:4.13.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}