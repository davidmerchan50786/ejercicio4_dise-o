package com.example.ejercicio4_diseo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ejercicio4_diseo.modelo.Usuario
import com.example.ejercicio4_diseo.modelo.Vehiculo

class EjercicioViewModel : ViewModel(){

    private val _usuario = MutableLiveData<Usuario?>()
    val usuario get() = _usuario

    private val _vehiculos = MutableLiveData<MutableList<Vehiculo>>()
    val vehiculos get() = _vehiculos

    init {
        _usuario.value = null
        _vehiculos.value = mutableListOf()

    }

    //Metodos para modificar datos

    fun setUsuario (nuevoUsuario: Usuario) {
        _usuario.value = nuevoUsuario
    }

    fun getUsuario(): Usuario? {
        return _usuario.value
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