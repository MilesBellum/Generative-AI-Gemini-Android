package com.eagb.generativeai_gemini_android

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.eagb.generativeai_gemini_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment

        navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = (
                supportFragmentManager.findFragmentById(
                    R.id.main_nav_host
                ) as NavHostFragment
                ).navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
