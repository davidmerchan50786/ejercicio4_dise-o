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

    fun insertar() {
        binding.buttonInsertar.performClick()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener referencia al ViewModel desde MainActivity
        viewModel = (activity as MainActivity).miViewModel

        binding.buttonInsertar.setOnClickListener {
            val nombre = binding.etNombre.text.toString().trim()
            val apellidos = binding.etApellidos.text.toString().trim()
            val edadTexto = binding.etEdad.text.toString().trim()

            // validamos que todos los campos estén rellenos
            var hayErrores = false
            val errores = mutableListOf<String>()

            if (nombre.isEmpty()) {
                errores.add("El nombre no puede estar vacío")
                hayErrores = true
            }
            if (apellidos.isEmpty()) {
                errores.add("Los apellidos no pueden estar vacíos")
                hayErrores = true
            }
            if (edadTexto.isEmpty()) {
                errores.add("La edad no puede estar vacía")
                hayErrores = true
            } else {
                val edad = edadTexto.toIntOrNull()
                if (edad == null) {
                    errores.add("La edad debe ser un número")
                    hayErrores = true
                } else if (edad < 16 || edad > 80) {
                    errores.add("La edad debe estar entre 16 y 80 años")
                    hayErrores = true
                }
            }

            if (hayErrores) {
                // mostramos todos los errores en un toast
                Toast.makeText(requireContext(), errores.joinToString("\n"), Toast.LENGTH_LONG).show()
            } else {
                // todo bien, creamos el usuario y volvemos al primero
                val edad = edadTexto.toInt()
                viewModel.setUsuario(Usuario(nombre, apellidos, edad))

                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}