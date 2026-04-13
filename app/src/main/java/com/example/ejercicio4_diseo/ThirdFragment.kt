package com.example.ejercicio4_diseo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicio4_diseo.databinding.FragmentThirdBinding
import com.example.ejercicio4_diseo.modelo.Vehiculo

class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EjercicioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener referencia al ViewModel desde MainActivity
        viewModel = (activity as MainActivity).miViewModel

        val usuario = viewModel.getUsuario()!!

        // lista de vehículos disponibles según la edad
        val vehiculosDisponibles = mutableListOf<Vehiculo>()

        if (usuario.edad >= 18) {
            vehiculosDisponibles.add(Vehiculo("Coche", "Coche 1", "Sedán compacto, ideal para ciudad", 15000.0))
            vehiculosDisponibles.add(Vehiculo("Coche", "Coche 2", "SUV familiar con gran maletero", 25000.0))
            vehiculosDisponibles.add(Vehiculo("Coche", "Coche 3", "Deportivo de alta gama", 45000.0))
        }
        vehiculosDisponibles.add(Vehiculo("Moto", "Moto 1", "Moto urbana 125cc, fácil de aparcar", 3500.0))
        vehiculosDisponibles.add(Vehiculo("Moto", "Moto 2", "Naked 500cc para carretera", 7000.0))
        vehiculosDisponibles.add(Vehiculo("Moto", "Moto 3", "Supermoto 750cc de alto rendimiento", 12000.0))

        val adapter = VehiculoAdapter(vehiculosDisponibles) { vehiculo ->
            // al pulsar un item navegamos al fragmento detalle pasando los datos
            val bundle = bundleOf(
                "nombre" to vehiculo.nombre,
                "tipo" to vehiculo.tipo,
                "descripcion" to vehiculo.descripcion,
                "precio" to vehiculo.precio
            )
            findNavController().navigate(R.id.action_ThirdFragment_to_DetalleVehiculoFragment, bundle)
        }

        binding.rvVehiculos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvVehiculos.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}