package com.example.ejercicio4_diseo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.ejercicio4_diseo.databinding.FragmentSecondBinding
import com.example.ejercicio4_diseo.modelo.Usuario

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EjercicioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener referencia al ViewModel desde MainActivity
        viewModel = (activity as MainActivity).miViewModel

        // Si ya hay usuario logeado, pre-rellenar los campos
        val usuarioActual = viewModel.getUsuario()
        if (usuarioActual != null) {
            binding.etNombre.setText(usuarioActual.nombre)
            binding.etApellidos.setText(usuarioActual.apellidos)
            binding.etEdad.setText(usuarioActual.edad.toString())
        }

        binding.buttonInsertar.setOnClickListener {
            val nombre = binding.etNombre.text.toString().trim()
            val apellidos = binding.etApellidos.text.toString().trim()
            val edadStr = binding.etEdad.text.toString().trim()

            // Validar que los campos no estén vacíos
            if (nombre.isEmpty() || apellidos.isEmpty() || edadStr.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Por favor, rellena todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Validar edad
            val edad = edadStr.toIntOrNull()
            if (edad == null || edad <= 0) {
                Toast.makeText(
                    requireContext(),
                    "Por favor, introduce una edad válida",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Crear usuario y guardarlo
            // setUsuario() ahora guarda automáticamente en SharedPreferences
            val nuevoUsuario = Usuario(nombre, apellidos, edad)
            viewModel.setUsuario(nuevoUsuario)

            Toast.makeText(
                requireContext(),
                "Datos guardados correctamente",
                Toast.LENGTH_SHORT
            ).show()

            // Navegar de vuelta a la pantalla principal
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}