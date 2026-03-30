package com.example.ejercicio4_diseo.modelo

class Usuario(
    val nombre: String,
    val apellidos: String,
    val edad: Int
) {
    private val vehiculos = mutableListOf<Vehiculo>()

    fun agregarVehiculo(vehiculo: Vehiculo) {
        vehiculos.add(vehiculo)
    }

    fun getVehiculos(): List<Vehiculo> = vehiculos
}