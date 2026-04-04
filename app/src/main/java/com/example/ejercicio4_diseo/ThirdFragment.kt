package com.example.ejercicio4_diseo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.ejercicio4_diseo.databinding.FragmentThirdBinding
import com.example.ejercicio4_diseo.modelo.Vehiculo

class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = activity as MainActivity
        val usuario = mainActivity.usuario!!

        // si tiene menos de 18 solo puede ver motos, ocultamos los coches
        if (usuario.edad < 18) {
            binding.cbCoche1.visibility = View.GONE
            binding.cbCoche2.visibility = View.GONE
            binding.cbCoche3.visibility = View.GONE
        }

        binding.btnComprar.setOnClickListener {
            val vehiculosSeleccionados = mutableListOf<Vehiculo>()

            // recogemos los checkboxes marcados según la edad
            if (usuario.edad >= 18) {
                if (binding.cbCoche1.isChecked) vehiculosSeleccionados.add(Vehiculo("Coche", "Coche 1"))
                if (binding.cbCoche2.isChecked) vehiculosSeleccionados.add(Vehiculo("Coche", "Coche 2"))
                if (binding.cbCoche3.isChecked) vehiculosSeleccionados.add(Vehiculo("Coche", "Coche 3"))
            }
            if (binding.cbMoto1.isChecked) vehiculosSeleccionados.add(Vehiculo("Moto", "Moto 1"))
            if (binding.cbMoto2.isChecked) vehiculosSeleccionados.add(Vehiculo("Moto", "Moto 2"))
            if (binding.cbMoto3.isChecked) vehiculosSeleccionados.add(Vehiculo("Moto", "Moto 3"))

            if (vehiculosSeleccionados.isEmpty()) {
                Toast.makeText(requireContext(), "Selecciona al menos un vehículo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // añadimos los vehículos al usuario
            vehiculosSeleccionados.forEach { usuario.agregarVehiculo(it) }

            // volvemos al primer fragmento
            findNavController().navigate(R.id.action_ThirdFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}