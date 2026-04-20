package com.example.ejercicio4_diseo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ejercicio4_diseo.modelo.Vehiculo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Vehiculo::class], version = 1, exportSchema = false)
abstract class VehiculosBBDD : RoomDatabase() {

    abstract fun vehiculoDAO(): VehiculoDAO

    companion object {
        @Volatile
        private var INSTANCE: VehiculosBBDD? = null

        fun getDatabase(context: Context): VehiculosBBDD {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VehiculosBBDD::class.java,
                    "vehiculos_database"
                )
                    .addCallback(DatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        poblarDatabase(database.vehiculoDAO())
                    }
                }
            }
        }

        suspend fun poblarDatabase(vehiculoDAO: VehiculoDAO) {
            // Eliminar datos existentes (por si acaso)
            vehiculoDAO.eliminarTodos()

            // Insertar vehículos iniciales
            val vehiculosIniciales = listOf(
                Vehiculo(
                    tipo = "Coche",
                    nombre = "Coche 1",
                    descripcion = "Sedán compacto, ideal para ciudad",
                    precio = 15000.0
                ),
                Vehiculo(
                    tipo = "Coche",
                    nombre = "Coche 2",
                    descripcion = "SUV familiar con gran maletero",
                    precio = 25000.0
                ),
                Vehiculo(
                    tipo = "Coche",
                    nombre = "Coche 3",
                    descripcion = "Deportivo de alta gama",
                    precio = 45000.0
                ),
                Vehiculo(
                    tipo = "Moto",
                    nombre = "Moto 1",
                    descripcion = "Moto urbana 125cc, fácil de aparcar",
                    precio = 3500.0
                ),
                Vehiculo(
                    tipo = "Moto",
                    nombre = "Moto 2",
                    descripcion = "Naked 500cc para carretera",
                    precio = 7000.0
                ),
                Vehiculo(
                    tipo = "Moto",
                    nombre = "Moto 3",
                    descripcion = "Supermoto 750cc de alto rendimiento",
                    precio = 12000.0
                )
            )

            vehiculoDAO.insertarVarios(vehiculosIniciales)
        }
    }
}