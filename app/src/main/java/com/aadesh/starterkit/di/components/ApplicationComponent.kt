package com.aadesh.starterkit.di.components

import android.content.Context
import com.aadesh.starterkit.App
import com.aadesh.starterkit.di.modules.ApplicationModule
import com.aadesh.starterkit.di.modules.DatabaseModule
import com.aadesh.starterkit.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    ApplicationModule::class,
    DatabaseModule::class,
    NetworkModule::class
  ]
)
interface ApplicationComponent {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun context(context: Context): Builder
    fun build(): ApplicationComponent
  }

  fun context(): Context

  fun inject(app: App)
}
