package com.example.ejercicio4_diseo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ejercicio4_diseo.modelo.Vehiculo
import kotlinx.coroutines.flow.Flow

@Dao
interface VehiculoDAO {

    /**
     * Obtener todos los vehículos de la base de datos
     * @return Flow que emite la lista de vehículos cada vez que cambia
     */
    @Query("SELECT * FROM vehiculos ORDER BY id ASC")
    fun mostrarVehiculos(): Flow<List<Vehiculo>>

    /**
     * Insertar un vehículo en la base de datos
     * Si ya existe (mismo id), lo reemplaza
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(vehiculo: Vehiculo)

    /**
     * Insertar múltiples vehículos a la vez
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarVarios(vehiculos: List<Vehiculo>)

    /**
     * Actualizar un vehículo existente
     */
    @Update
    suspend fun actualizar(vehiculo: Vehiculo)

    /**
     * Eliminar un vehículo de la base de datos
     */
    @Delete
    suspend fun eliminar(vehiculo: Vehiculo)

    /**
     * Eliminar todos los vehículos
     */
    @Query("DELETE FROM vehiculos")
    suspend fun eliminarTodos()

    /**
     * Obtener un vehículo por su ID
     */
    @Query("SELECT * FROM vehiculos WHERE id = :vehiculoId")
    suspend fun obtenerPorId(vehiculoId: Int): Vehiculo?

    /**
     * Buscar vehículos por tipo (Coche o Moto)
     */
    @Query("SELECT * FROM vehiculos WHERE tipo = :tipo ORDER BY precio ASC")
    fun buscarPorTipo(tipo: String): Flow<List<Vehiculo>>
}