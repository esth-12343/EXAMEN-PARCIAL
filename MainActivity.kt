package com.example.juegodecolores_examenfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Conecta la actividad principal con su diseño visual
        setContentView(R.layout.activity_main)

        // Configura la navegación entre los fragments (Welcome, Game, Result)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Esto hace que la barra superior (si la tienes) muestre el título del fragment actual
        setupActionBarWithNavController(navController)
    }

    // Permite que el botón "Atrás" funcione correctamente entre fragments
    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
