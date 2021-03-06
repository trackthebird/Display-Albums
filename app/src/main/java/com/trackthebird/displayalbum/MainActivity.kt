package com.trackthebird.displayalbum

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.Bindable
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.trackthebird.displayalbum.databinding.ActivityMainBinding
import com.trackthebird.displayalbum.network.ApiInterface

class MainActivity : AppCompatActivity() {

    private val TAG : String = "MainActivity"

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var appBarConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        with(mBinding) {
            setSupportActionBar(toolbar)
            val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.navigation_host_fragment) as NavHostFragment? ?: return
            with(host){
                appBarConfiguration = AppBarConfiguration(navController.graph)
                setupActionBarWithNavController(navController, appBarConfiguration)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navigation_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
