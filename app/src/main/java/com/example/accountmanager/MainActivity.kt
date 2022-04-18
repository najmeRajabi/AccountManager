package com.example.accountmanager

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
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
                R.id.createAccountFragment,R.id.selectAccountFragment,
            R.id.deleteAllAccountBtn),
            binding.drawerLayout )
        toolbar.setupWithNavController(navController,appBarConfiguration)
        binding.navView.setupWithNavController(navController)
        setSupportActionBar(toolbar)

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId){

                R.id.deleteAllAccountBtn -> {
                    showDefaultDialog()
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

    private fun showDefaultDialog() {
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.apply {
//            setIcon(R.drawable.ic_hello)
            setTitle("مطمئن هستید؟؟")
            setMessage("تمامی حساب های ذخیره شده شما پاک خواهد شد!")
            setPositiveButton("مطمئنم") { _, _ ->
                toast("deleted")
                vModel.deleteAll()
            }
            setNegativeButton("نه") { _, _ ->
                // dismiss
            }

        }.create().show()

    }

    private fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}