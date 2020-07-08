package com.example.latihan2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.main_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(top_appbar)

        NavigationUI.setupActionBarWithNavController(this, navController)

        /*change title appbar not use label in nav*/
        navController.addOnDestinationChangedListener{ controller, destination, arguments ->
            val appbar = supportActionBar

            title = when(destination.id){
                R.id.homeFragment -> "Pengingat"
                R.id.createFragment -> "Create"
                R.id.detailFragment -> "Detail"
                else -> "wadidaw"
            }

            appbar?.title = title
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}