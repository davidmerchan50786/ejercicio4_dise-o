package com.example.ejercicio4_diseo

import android.os.Bundle
import androidx.activity.viewModels
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.ejercicio4_diseo.databinding.ActivityMainBinding
import com.example.ejercicio4_diseo.modelo.Usuario

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var usuario: Usuario? = null
    private lateinit var navController: NavController

    val miViewModel : EjercicioViewModel by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.hide()

        navController = findNavController(R.id.nav_host_fragment_content_main)
        binding.fab.visibility = View.GONE

        navController.addOnDestinationChangedListener { _, _, _ ->
            invalidateOptionsMenu()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return when (navController.currentDestination?.id) {
            R.id.FirstFragment -> {
                menuInflater.inflate(R.menu.menu_first, menu)
                true
            }
            R.id.SecondFragment -> {
                menuInflater.inflate(R.menu.menu_second, menu)
                true
            }
            // ThirdFragment y DetalleVehiculoFragment sin menú
            else -> false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.menu_insertar_datos -> {
                navController.navigate(R.id.action_FirstFragment_to_SecondFragment)
                true
            }

            R.id.menu_comprar -> {
                if (usuario == null) {
                    Toast.makeText(this, "Primero tienes que insertar los datos", Toast.LENGTH_SHORT).show()
                } else {
                    navController.navigate(R.id.action_FirstFragment_to_ThirdFragment)
                }
                true
            }

            R.id.menu_insertar -> {
                val fragment = supportFragmentManager.primaryNavigationFragment
                    ?.childFragmentManager?.primaryNavigationFragment
                if (fragment is SecondFragment) fragment.insertar()
                true
            }

            R.id.menu_volver -> {
                navController.popBackStack()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}