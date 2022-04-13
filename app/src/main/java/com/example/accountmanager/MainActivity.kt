package com.example.accountmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import androidx.navigation.ui.AppBarConfiguration
import com.example.accountmanager.account.AccountViewModel
import com.example.accountmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val vModel :AccountViewModel by viewModels()
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        val  toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf( R.id.showProfileFragment, R.id.showAccountFragment,
                R.id.createAccountFragment,R.id.chooseAccountFragment,
            R.id.deleteAllAccountBtn),
            binding.drawerLayout )
        toolbar.setupWithNavController(navController,appBarConfiguration)
        binding.navView.setupWithNavController(navController)
        setSupportActionBar(toolbar)

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId){

                R.id.deleteAllAccountBtn -> {
                    //todo dialog sure
                   vModel.deleteAll()
                    Toast.makeText(this,"deleted",Toast.LENGTH_SHORT).show()
                    true
                }
            }
            NavigationUI.onNavDestinationSelected(it, navController)
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}