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

        viewModel = (activity as MainActivity).miViewModel
        val usuario = viewModel.getUsuario()!!

        val vehiculosDisponibles = mutableListOf<Vehiculo>()

        if (usuario.edad >= 18) {
            vehiculosDisponibles.add(
                Vehiculo(
                    tipo = "Coche",
                    nombre = "Coche 1",
                    descripcion = "Sedán compacto, ideal para ciudad",
                    precio = 15000.0
                )
            )
            vehiculosDisponibles.add(
                Vehiculo(
                    tipo = "Coche",
                    nombre = "Coche 2",
                    descripcion = "SUV familiar con gran maletero",
                    precio = 25000.0
                )
            )
            vehiculosDisponibles.add(
                Vehiculo(
                    tipo = "Coche",
                    nombre = "Coche 3",
                    descripcion = "Deportivo de alta gama",
                    precio = 45000.0
                )
            )
        }

        vehiculosDisponibles.add(
            Vehiculo(
                tipo = "Moto",
                nombre = "Moto 1",
                descripcion = "Moto urbana 125cc, fácil de aparcar",
                precio = 3500.0
            )
        )
        vehiculosDisponibles.add(
            Vehiculo(
                tipo = "Moto",
                nombre = "Moto 2",
                descripcion = "Naked 500cc para carretera",
                precio = 7000.0
            )
        )
        vehiculosDisponibles.add(
            Vehiculo(
                tipo = "Moto",
                nombre = "Moto 3",
                descripcion = "Supermoto 750cc de alto rendimiento",
                precio = 12000.0
            )
        )

        val adapter = VehiculoAdapter(vehiculosDisponibles) { vehiculo ->
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