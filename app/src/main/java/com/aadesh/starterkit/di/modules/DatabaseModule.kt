package com.aadesh.starterkit.di.modules

import android.content.Context
import com.aadesh.starterkit.utils.SharedPreferenceUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
  @Provides
  @Singleton
  fun providePreferencesHelper(@ApplicationContext context: Context): SharedPreferenceUtil =
    SharedPreferenceUtil(context)
}
