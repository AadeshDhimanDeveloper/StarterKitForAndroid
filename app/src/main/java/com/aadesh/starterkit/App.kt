package com.aadesh.starterkit

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.StrictMode
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

  override fun attachBaseContext(base: Context) {
    super.attachBaseContext(base)
    if (BuildConfig.DEBUG) {
      MultiDex.install(this)
    }
  }

  override fun onCreate() {
    super.onCreate()
    configureStrictMode()
  }

  private fun configureStrictMode() {
    if (BuildConfig.DEBUG) {
      StrictMode.setThreadPolicy(
        StrictMode.ThreadPolicy.Builder().apply {
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            detectResourceMismatches()
          }
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            detectUnbufferedIo()
          }
          detectCustomSlowCalls()
          detectDiskReads()
          detectDiskWrites()
          detectNetwork()
          penaltyFlashScreen()
          penaltyLog()
        }.build()
      )
      StrictMode.setVmPolicy(
        StrictMode.VmPolicy.Builder().apply {
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            detectCleartextNetwork()
          }
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            detectContentUriWithoutPermission()
          }
          detectFileUriExposure()
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            detectNonSdkApiUsage()
          }
          detectActivityLeaks()
          detectLeakedClosableObjects()
          detectLeakedSqlLiteObjects()
          penaltyLog()
          detectLeakedRegistrationObjects()
        }.build()
      )
    }
  }
}
