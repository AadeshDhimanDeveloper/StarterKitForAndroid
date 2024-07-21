import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
  alias(libs.plugins.detekt)
  alias(libs.plugins.ktlint)
  alias(libs.plugins.dagger.hilt.android.plugin)
  id("kotlin-kapt")
}

apply(from = "$rootDir/team-props/git-hooks.gradle")

android {
  namespace = "com.aadesh.starterkit"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.aadesh.starterkit"
    minSdk = 21
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
    debug {
      multiDexEnabled = true
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
  // detekt configurations
  tasks.withType<Detekt>().configureEach {
    jvmTarget = "1.8"
    reports {
      html.required.set(true)
      xml.required.set(true)
    }
  }
  tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = "1.8"
  }
  // end detekt configuration
  buildFeatures {
    // for using viewBinding in our project.
    viewBinding = true
    buildConfig = true
  }

  lint {
    // Enable lint checks during debug builds (recommended)
    checkReleaseBuilds = false
    // Abort the build if errors are found
    abortOnError = true
    // Treat lint warnings as errors to maintain code quality
    warningsAsErrors = true
    // Check dependencies for potential issues
    checkDependencies = true

    disable.apply {
      // add your desire lint error to ignore, if you want to ignore
      // any warning from lint check.
      add("GoogleAppIndexingApiWarning")
    }
    // Specify a lint configuration file for more advanced configurations
    lintConfig = rootProject.file("lintConfig.xml")
  }
}

detekt {
  buildUponDefaultConfig = true
  allRules = false
  // point to your custom config defining rules to run, overwriting default behavior
  config.setFrom("$rootDir/config/detekt/detekt.yml")
  // a way of suppressing issues before introducing detekt.
  // see how detekt works https://detekt.dev/docs/introduction/suppressing-rules
  baseline = project.file("detekt_baseline.xml")
}

ktlint {
  android.set(true)
}

kapt {
  correctErrorTypes = true
}

dependencies {
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.constraintlayout)
  implementation(libs.androidx.lifecycle.livedata.ktx)
  implementation(libs.androidx.lifecycle.viewmodel.ktx)
  implementation(libs.androidx.navigation.fragment.ktx)
  implementation(libs.androidx.navigation.ui.ktx)
  implementation(libs.multidex)
  implementation(libs.retrofit)
  implementation(libs.retrofit)
  implementation(libs.retrofit.gson.convertor)
  implementation(libs.glide)
  implementation(libs.dagger.hilt)
  kapt(libs.dagger.hilt.compiler)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}
