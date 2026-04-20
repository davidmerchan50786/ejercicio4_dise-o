package com.example.ejercicio4_diseo.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehiculos")
data class Vehiculo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tipo: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double
)