package com.aramtory.stumeet

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.aramtory.stumeet.coreui.base.BindingActivity
import com.aramtory.stumeet.data.SharedManager
import com.aramtory.stumeet.databinding.ActivityMainBinding

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDarkMode()
        initView()
    }

    override fun initView() {
        initMainBottomNavigation()
    }

    private fun initMainBottomNavigation() {
        val navController =
            (supportFragmentManager.findFragmentById(R.id.container_main) as NavHostFragment).findNavController()
        binding.navigationMain.apply {
            setupWithNavController(navController)
            itemIconTintList = null
        }
        if (!SharedManager.checkLogin()) navController.navigate(R.id.fragment_login)
        setBottomNaviVisible(navController)
    }

    private fun setBottomNaviVisible(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.navigationMain.visibility =
                if (destination.id in
                    listOf(
                        R.id.navigation_home,
                        R.id.navigation_study,
                        R.id.navigation_calendar,
                        R.id.navigation_my
                    )
                ) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
        }
    }

    private fun setDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}