package com.example.ejercicio4_diseo.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.ejercicio4_diseo.modelo.Usuario

class PreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "usuario_prefs"
        private const val KEY_NOMBRE = "nombre"
        private const val KEY_APELLIDOS = "apellidos"
        private const val KEY_EDAD = "edad"
        private const val KEY_IS_LOGGED = "is_logged"
    }

    fun guardarUsuario(usuario: Usuario) {
        sharedPreferences.edit().apply {
            putString(KEY_NOMBRE, usuario.nombre)
            putString(KEY_APELLIDOS, usuario.apellidos)
            putInt(KEY_EDAD, usuario.edad)
            putBoolean(KEY_IS_LOGGED, true)
            apply()
        }
    }

    fun obtenerUsuario(): Usuario? {
        if (!isLogged()) return null

        val nombre = sharedPreferences.getString(KEY_NOMBRE, null)
        val apellidos = sharedPreferences.getString(KEY_APELLIDOS, null)
        val edad = sharedPreferences.getInt(KEY_EDAD, 0)

        return if (nombre != null && apellidos != null && edad > 0) {
            Usuario(nombre, apellidos, edad)
        } else {
            null
        }
    }

    fun isLogged(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED, false)
    }

    fun logout() {
        sharedPreferences.edit().clear().apply()
    }
}