package com.aadesh.starterkit

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.aadesh.starterkit.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var binding: ActivityMainBinding

  private val navController by lazy {
    (
      supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)
        as NavHostFragment
      ).navController
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setSupportActionBar(binding.appBarMain.toolbar)

    setupFab()
    setupNavigation()
  }

  private fun setupFab() {
    binding.appBarMain.fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).setAnchorView(R.id.fab).show()
    }
  }

  private fun setupNavigation() {
    val drawerLayout: DrawerLayout = binding.drawerLayout
    val navView: NavigationView = binding.navView

    // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
    appBarConfiguration = AppBarConfiguration(
      setOf(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow),
      drawerLayout
    )
    setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.main, menu)
    return true
  }

  override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment_content_main)
    return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
  }
}
