package com.example.ejercicio4_diseo.data

import com.example.ejercicio4_diseo.modelo.Vehiculo
import kotlinx.coroutines.flow.Flow

class Repositorio(private val vehiculoDAO: VehiculoDAO) {

    /**
     * Obtener todos los vehículos
     * Flow que emite automáticamente cuando hay cambios en la BBDD
     */
    fun mostrarVehiculos(): Flow<List<Vehiculo>> {
        return vehiculoDAO.mostrarVehiculos()
    }

    /**
     * Insertar un vehículo
     */
    suspend fun insertar(vehiculo: Vehiculo) {
        vehiculoDAO.insertar(vehiculo)
    }

    /**
     * Insertar varios vehículos a la vez
     */
    suspend fun insertarVarios(vehiculos: List<Vehiculo>) {
        vehiculoDAO.insertarVarios(vehiculos)
    }

    /**
     * Actualizar un vehículo existente
     */
    suspend fun actualizar(vehiculo: Vehiculo) {
        vehiculoDAO.actualizar(vehiculo)
    }

    /**
     * Eliminar un vehículo
     */
    suspend fun eliminar(vehiculo: Vehiculo) {
        vehiculoDAO.eliminar(vehiculo)
    }

    /**
     * Eliminar todos los vehículos
     */
    suspend fun eliminarTodos() {
        vehiculoDAO.eliminarTodos()
    }

    /**
     * Obtener vehículo por ID
     */
    suspend fun obtenerPorId(vehiculoId: Int): Vehiculo? {
        return vehiculoDAO.obtenerPorId(vehiculoId)
    }

    /**
     * Buscar vehículos por tipo (Coche o Moto)
     */
    fun buscarPorTipo(tipo: String): Flow<List<Vehiculo>> {
        return vehiculoDAO.buscarPorTipo(tipo)
    }
}