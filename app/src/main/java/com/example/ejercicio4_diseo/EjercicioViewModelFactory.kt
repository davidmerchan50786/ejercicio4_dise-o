package com.example.ejercicio4_diseo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ejercicio4_diseo.data.Repositorio
import com.example.ejercicio4_diseo.utils.PreferencesManager

class EjercicioViewModelFactory(
    private val preferencesManager: PreferencesManager,
    private val repositorio: Repositorio  // NUEVO parámetro
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EjercicioViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EjercicioViewModel(preferencesManager, repositorio) as T
        }
        throw IllegalArgumentException("ViewModel class desconocida")
    }
}