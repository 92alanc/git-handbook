plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.services)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelise)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.braincorp.githandbook"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.braincorp.githandbook"
        minSdk = 19
        targetSdk = 34
        versionCode = 17
        versionName = "2024.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    signingConfigs {
        create("release") {
            keyAlias = properties["git_handbook_key_alias"] as String
            keyPassword = properties["git_handbook_key_password"] as String
            storeFile = file(path = properties["git_handbook_store_file"] as String)
            storePassword = properties["git_handbook_store_password"] as String
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }

        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    @Suppress("UnstableApiUsage")
    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(libs.android.appcompat)
    implementation(libs.android.cardview)
    implementation(libs.android.material)
    implementation(libs.android.constraintlayout)
    implementation(libs.android.ump)
    implementation(libs.google.ads)
    implementation(libs.koin.android)
    implementation(libs.koin.core)
    implementation(libs.room.ktx)

    ksp(libs.room.compiler)

    androidTestImplementation(libs.android.espresso.core)
    androidTestImplementation(libs.android.test.runner)
    androidTestImplementation(libs.truth)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
}
