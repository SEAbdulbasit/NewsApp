@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.newsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/*"
        }
    }

    flavorDimensions += "source"

    productFlavors {
        create("al-jazeera") {
            dimension = "source"
            applicationIdSuffix = ".aljazeeraenglish"
            buildConfigField(
                "String", "NEWS_SOURCE", "\"al-jazeera-english\""
            )
            buildConfigField(
                "String", "NEWS_SOURCE_TITLE", "\"Al Jazeera\""
            )
        }

        create("abc-news") {
            dimension = "source"
            applicationIdSuffix = ".abcnews"
            buildConfigField(
                "String", "NEWS_SOURCE", "\"abc-news\""
            )
            buildConfigField(
                "String", "NEWS_SOURCE_TITLE", "\"ABC News\""
            )
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)


    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)
    implementation(libs.converter.moshi)

    //Moshi
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi)
    kapt(libs.moshi.kotlin.codegen)

    //Hilt
    implementation(libs.hilt.android)

    //mockk
    androidTestImplementation(libs.mockk)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk.android)

    //coroutine test
    testImplementation(libs.kotlinx.coroutines.test)

    //turbine
    testImplementation(libs.turbine)

    //testing
    androidTestImplementation(libs.core.testing)
    testImplementation(libs.core.testing)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material)

    implementation(libs.coil.compose)

    implementation(libs.androidx.biometric)


}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}