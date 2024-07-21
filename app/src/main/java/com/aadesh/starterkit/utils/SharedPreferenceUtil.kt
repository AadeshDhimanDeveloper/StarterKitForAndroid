package com.aadesh.starterkit.utils

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceUtil @Inject constructor(context: Context) {

  private val sharedPreferences: SharedPreferences =
    context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

  fun saveUserName(value: String) {
    sharedPreferences.edit().putString(USER_NAME, value).apply()
  }

  fun getUserName(): String? {
    return sharedPreferences.getString(USER_NAME, null)
  }

  companion object {
    private const val PREFS_NAME = "starter_kit_prefs"
    private const val USER_NAME = "key_sample"
  }
}
