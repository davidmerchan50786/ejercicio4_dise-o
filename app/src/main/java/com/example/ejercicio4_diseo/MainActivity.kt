package com.example.ejercicio4_diseo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.ejercicio4_diseo.databinding.ActivityMainBinding
import com.example.ejercicio4_diseo.utils.PreferencesManager

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    // ¡NUEVO! Inicializar PreferencesManager
    private val preferencesManager by lazy {
        PreferencesManager(this)
    }

    // ¡CAMBIO! ViewModel con Factory
    val miViewModel: EjercicioViewModel by viewModels {
        EjercicioViewModelFactory(preferencesManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    // ¡NUEVO! Crear menú
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // ¡NUEVO! Mostrar/ocultar logout según el fragmento
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val currentDestination = navController.currentDestination?.id

        // Ocultar logout en SecondFragment
        val logoutItem = menu.findItem(R.id.action_logout)
        logoutItem?.isVisible = currentDestination != R.id.SecondFragment

        return super.onPrepareOptionsMenu(menu)
    }

    // ¡NUEVO! Manejar click en logout
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                mostrarDialogoLogout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // ¡NUEVO! Diálogo de confirmación
    private fun mostrarDialogoLogout() {
        AlertDialog.Builder(this)
            .setTitle("Cerrar sesión")
            .setMessage("¿Estás seguro de que quieres cerrar sesión?")
            .setPositiveButton("Sí") { _, _ ->
                // Hacer logout
                miViewModel.logout()

                // Navegar al FirstFragment
                val navController = findNavController(R.id.nav_host_fragment_content_main)
                navController.navigate(R.id.FirstFragment)
            }
            .setNegativeButton("No", null)
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}