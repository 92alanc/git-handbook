plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.services)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelise)
    alias(libs.plugins.ksp)
}

android {
    namespace = Config.App.PACKAGE_NAME
    compileSdk = Config.Build.TARGET_SDK

    defaultConfig {
        applicationId = Config.App.PACKAGE_NAME
        minSdk = Config.Build.MIN_SDK
        targetSdk = Config.Build.TARGET_SDK
        versionCode = Config.App.VERSION_CODE
        versionName = Config.App.VERSION_NAME

        testInstrumentationRunner = Config.Testing.TEST_RUNNER_NAME
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
        viewBinding = Config.Build.ENABLE_VIEW_BINDING
    }

    @Suppress("UnstableApiUsage")
    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }

    compileOptions {
        sourceCompatibility = Config.Build.javaVersion
        targetCompatibility = Config.Build.javaVersion
    }

    kotlinOptions {
        jvmTarget = Config.Build.javaVersionString
    }
}

dependencies {
    implementation(libs.android.activity)
    implementation(libs.android.appcompat)
    implementation(libs.android.cardview)
    implementation(libs.android.material)
    implementation(libs.android.constraintlayout)
    implementation(libs.android.ump)
    implementation(libs.google.ads)
    implementation(libs.hilt.android)
    implementation(libs.room.ktx)

    kapt(libs.hilt.compiler)
    ksp(libs.room.compiler)

    androidTestImplementation(libs.android.espresso.core)
    androidTestImplementation(libs.android.test.runner)
    androidTestImplementation(libs.truth)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
}
