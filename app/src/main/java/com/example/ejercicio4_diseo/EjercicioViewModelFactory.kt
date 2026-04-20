package com.example.ejercicio4_diseo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ejercicio4_diseo.utils.PreferencesManager

/**
 * Factory para crear instancias de EjercicioViewModel con parámetros
 * Necesario porque el ViewModel requiere PreferencesManager en el constructor
 */
class EjercicioViewModelFactory(
    private val preferencesManager: PreferencesManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EjercicioViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EjercicioViewModel(preferencesManager) as T
        }
        throw IllegalArgumentException("ViewModel class desconocida")
    }
}