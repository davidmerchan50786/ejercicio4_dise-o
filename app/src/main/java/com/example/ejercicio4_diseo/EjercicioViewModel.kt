package com.example.ejercicio4_diseo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ejercicio4_diseo.modelo.Usuario
import com.example.ejercicio4_diseo.modelo.Vehiculo
import com.example.ejercicio4_diseo.utils.PreferencesManager

class EjercicioViewModel(private val preferencesManager: PreferencesManager) : ViewModel() {

    private val _usuario = MutableLiveData<Usuario?>()
    val usuario get() = _usuario

    private val _vehiculos = MutableLiveData<MutableList<Vehiculo>>()
    val vehiculos get() = _vehiculos

    init {
        _usuario.value = preferencesManager.obtenerUsuario() //Carga datos guardados
        _vehiculos.value = mutableListOf()

    }

    //Metodos para modificar datos

    fun setUsuario (nuevoUsuario: Usuario) {
        _usuario.value = nuevoUsuario
        preferencesManager.guardarUsuario(nuevoUsuario) //Guarda en SharedPreferences
    }

    fun getUsuario(): Usuario? {
        return _usuario.value
    }

    // Verifica si hay usuario logeado
    fun isLogged(): Boolean {
        return preferencesManager.isLogged()
    }

    // Metodo para hacer logout

    fun logout() {
        _usuario.value = null
        _vehiculos.value = mutableListOf()
        preferencesManager.logout()
    }

    fun agregarVehiculo(vehiculo: Vehiculo){
        val lista = _vehiculos.value ?: mutableListOf()
        lista.add(vehiculo)
        _vehiculos.value = lista
    }

    fun getVehiculos(): List<Vehiculo> {
        return _vehiculos.value ?: emptyList()

    }



}