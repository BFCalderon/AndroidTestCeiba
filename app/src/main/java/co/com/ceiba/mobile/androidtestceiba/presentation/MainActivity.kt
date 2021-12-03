package co.com.ceiba.mobile.androidtestceiba.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import co.com.ceiba.mobile.androidtestceiba.R
import co.com.ceiba.mobile.androidtestceiba.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

  private lateinit var appBarConfiguration : AppBarConfiguration
  private lateinit var binding : ActivityMainBinding

  override fun onCreate(savedInstanceState : Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setSupportActionBar(binding.toolbar)

    val navController = findNavController(R.id.nav_host_fragment_content_post)
    appBarConfiguration = AppBarConfiguration(navController.graph)
    setupActionBarWithNavController(navController, appBarConfiguration)

//    supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment.newInstance()).commitNow()
  }

  override fun onSupportNavigateUp() : Boolean {
    val navController = findNavController(R.id.nav_host_fragment_content_post)
    return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
  }
}