package com.example.ejercicio4_diseo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.ejercicio4_diseo.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EjercicioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener referencia al ViewModel desde MainActivity
        viewModel = (activity as MainActivity).miViewModel

        // actualizamos el saludo cada vez que volvemos a este fragmento
        actualizarSaludo()

        binding.btnIrAInsertarDatos.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.btnIrAComprar.setOnClickListener {
            // Si el usuario está logeado, puede ir directo a comprar
            if (viewModel.isLogged()) {
                findNavController().navigate(R.id.action_FirstFragment_to_ThirdFragment)
            } else {
                // Si no está logeado, primero debe insertar datos
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // actualizamos al volver de otros fragmentos
        actualizarSaludo()
    }

    private fun actualizarSaludo() {
        val usuario = viewModel.getUsuario()

        if (usuario != null) {
            // mostramos bienvenida con nombre y apellidos
            var saludo = "¡Bienvenid@, ${usuario.nombre} ${usuario.apellidos}!"

            // si tiene vehículos comprados los mostramos también
            val vehiculos = viewModel.getVehiculos()
            if (vehiculos.isNotEmpty()) {
                val nombresVehiculos = vehiculos.joinToString(", ") { it.nombre }
                saludo += "\nVehículos comprados: $nombresVehiculos"
            }

            binding.txtSaludoBienvenida.text = saludo
        } else {
            binding.txtSaludoBienvenida.text = "¡Bienvenid@!"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}