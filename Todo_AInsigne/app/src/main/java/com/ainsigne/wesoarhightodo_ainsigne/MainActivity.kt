package com.ainsigne.wesoarhightodo_ainsigne

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import com.ainsigne.wesoarhightodo_ainsigne.api.TodoAPI
import com.ainsigne.wesoarhightodo_ainsigne.databinding.ActivityMainBinding
import com.ainsigne.wesoarhightodo_ainsigne.di.ActivityComponent
import com.ainsigne.wesoarhightodo_ainsigne.di.ActivityModule
import com.ainsigne.wesoarhightodo_ainsigne.di.DaggerActivityComponent
import com.ainsigne.wesoarhightodo_ainsigne.di.DatabaseModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    lateinit var activityComponent: ActivityComponent
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent.builder().activityModule(ActivityModule(this)).databaseModule(
            DatabaseModule(applicationContext)
        ).build()
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        drawerLayout = binding.drawerLayout
        navController = Navigation.findNavController(this, R.id.task_nav_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navigationView.setupWithNavController(navController)

    }
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}