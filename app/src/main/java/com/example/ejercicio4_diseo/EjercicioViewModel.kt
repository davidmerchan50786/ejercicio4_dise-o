package com.example.ejercicio4_diseo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.ejercicio4_diseo.data.Repositorio
import com.example.ejercicio4_diseo.modelo.Usuario
import com.example.ejercicio4_diseo.modelo.Vehiculo
import com.example.ejercicio4_diseo.utils.PreferencesManager
import kotlinx.coroutines.launch

class EjercicioViewModel(
    private val preferencesManager: PreferencesManager,
    private val repositorio: Repositorio
) : ViewModel() {

    //USUARIO-SharedPreferences

    private val _usuario = MutableLiveData<Usuario?>()
    val usuario: LiveData<Usuario?> get() = _usuario

    init {
        // Cargar usuario desde SharedPreferences
        _usuario.value = preferencesManager.obtenerUsuario()
    }

    fun setUsuario(nuevoUsuario: Usuario) {
        _usuario.value = nuevoUsuario
        preferencesManager.guardarUsuario(nuevoUsuario)
    }

    fun getUsuario(): Usuario? {
        return _usuario.value
    }

    fun isLogged(): Boolean {
        return preferencesManager.isLogged()
    }

    fun logout() {
        _usuario.value = null
        preferencesManager.logout()
    }

    // VEHÍCULOS-Room Database

    /**
     * LiveData reactivo desde la base de datos
     */
    val vehiculos: LiveData<List<Vehiculo>> =
        repositorio.mostrarVehiculos().asLiveData()

    /**
     * Agregar vehículo a la base de datos
     */
    fun agregarVehiculo(vehiculo: Vehiculo) = viewModelScope.launch {
        repositorio.insertar(vehiculo)
    }

    /**
     * Obtener lista actual de vehículos
     */
    fun getVehiculos(): List<Vehiculo> {
        return vehiculos.value ?: emptyList()
    }
}