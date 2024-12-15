package com.aramtory.stumeet

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.aramtory.stumeet.databinding.ActivityMainBinding


class MainActivity :
    com.aramtory.stumeet.coreui.base.BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDarkMode()
        initView()
    }

    override fun initView() {
        val navController =
            supportFragmentManager.findFragmentById(R.id.container_main)?.findNavController()

        with(binding) {
            navigationMain.itemIconTintList = null
            navController?.let {
                navigationMain.setupWithNavController(it)
            }
        }
    }

    private fun setDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}