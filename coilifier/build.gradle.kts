plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Configuration.compileSdk)
    buildToolsVersion(Configuration.buildTools)

    defaultConfig {
        minSdkVersion(Configuration.minSdk)
        targetSdkVersion(Configuration.targetSdk)
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFile("consumer-rules.pro")
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
            isShrinkResources = false
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
    testImplementation("junit:junit:4.13.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    api("com.github.bumptech.glide:glide:${Versions.glide}")
    kapt("com.github.bumptech.glide:compiler:${Versions.glide}")
}