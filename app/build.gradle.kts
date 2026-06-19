plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    id("androidx.navigation.safeargs.kotlin")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.21"
    alias(libs.plugins.ksp)
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.gioiovashvili"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.gioiovashvili"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://mocki.io/v1/\"")
        }
        getByName("debug") {
            buildConfigField("String", "BASE_URL", "\"https://mocki.io/v1/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    packagingOptions {
        resources.excludes.add("META-INF/versions/9/OSGI-INF/MANIFEST.MF")
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    dependencies {
        // Core & Lifecycle
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.activity.compose)

        // Jetpack Compose
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.ui)
        implementation(libs.androidx.ui.graphics)
        implementation(libs.androidx.material3)
        implementation(libs.androidx.ui.tooling.preview)
        debugImplementation(libs.androidx.ui.tooling)
        implementation(libs.androidx.navigation.compose)

        // Networking (Retrofit, OkHttp)
        implementation(libs.retrofit2.kotlinx.serialization.converter)
        implementation(libs.logging.interceptor)
        implementation(libs.kotlinx.serialization.json)

        // Dependency Injection (Hilt)
        implementation(libs.hilt.android)
        ksp(libs.hilt.android.compiler)
        implementation(libs.androidx.hilt.navigation.compose)

        // Image Loading (Coil)
        implementation(libs.coil)
        implementation(libs.coil.svg)
        implementation(libs.coil.compose)

        // DataStore
        implementation(libs.androidx.datastore.preferences)

        // Testing
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.ui.test.junit4)
        debugImplementation(libs.androidx.ui.test.manifest)

        testImplementation(libs.junit)
        testImplementation(libs.mockk)
        testImplementation(libs.kotlinx.coroutines.test)
        testImplementation(libs.turbine)
        testImplementation(libs.androidx.core.testing)
    }
}