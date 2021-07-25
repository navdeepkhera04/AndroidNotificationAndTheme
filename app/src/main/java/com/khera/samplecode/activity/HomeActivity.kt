package com.khera.samplecode.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.khera.samplecode.R
import com.khera.samplecode.base.BaseActivity
import com.khera.samplecode.utils.CommonUtils.disableDarkMode
import com.khera.samplecode.utils.CommonUtils.enableDarkMode
import com.khera.samplecode.utils.CommonUtils.isDarkMode

class HomeActivity : BaseActivity() {
    private var mAppBarConfiguration: AppBarConfiguration? = null
    private var drawer: DrawerLayout? = null
    private var toolbar: Toolbar? = null
    private var isDarkMode = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        findViewById()
        setSupportActionBar(toolbar)
        setUpDrawer()
        setClickListeners()
        init()
    }

    private fun init() {
        isDarkMode = isDarkMode(activity)
    }

    private fun findViewById() {
        drawer = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
    }

    private fun setClickListeners() {}
    private fun setUpDrawer() {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        mAppBarConfiguration = AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_notifications)
                .setDrawerLayout(drawer)
                .build()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration!!)
        NavigationUI.setupWithNavController(navigationView, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    @SuppressLint("NonConstantResourceId")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_theme -> {
                if (isDarkMode) {
                    disableDarkMode()
                } else {
                    enableDarkMode()
                }
                false
            }
            else -> false
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        invalidateOptionsMenu()
        if (isDarkMode) {
            menu.findItem(R.id.nav_theme).title = "Light Theme"
        } else {
            menu.findItem(R.id.nav_theme).title = "Dark Theme"
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, mAppBarConfiguration!!) || super.onSupportNavigateUp()
    }
}