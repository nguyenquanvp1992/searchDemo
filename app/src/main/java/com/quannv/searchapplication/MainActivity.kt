package com.quannv.searchapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.quannv.searchapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       getNavController()?.let {
           val appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment, R.id.detailFragment))
           setSupportActionBar(binding.toolbar)
           binding.toolbar.setupWithNavController(it, appBarConfiguration)
           NavigationUI.setupActionBarWithNavController(this, it, appBarConfiguration)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return getNavController()?.navigateUp() ?: super.onSupportNavigateUp()
    }

    fun getNavController(): NavController? {
        return (supportFragmentManager.findFragmentById(R.id.container) as? NavHostFragment)?.navController
    }
}