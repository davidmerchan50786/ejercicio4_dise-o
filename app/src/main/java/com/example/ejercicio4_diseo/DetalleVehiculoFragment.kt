package com.example.ejercicio4_diseo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.ejercicio4_diseo.databinding.FragmentDetalleVehiculoBinding
import com.example.ejercicio4_diseo.modelo.Vehiculo

class DetalleVehiculoFragment : Fragment() {

    private var _binding: FragmentDetalleVehiculoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalleVehiculoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recogemos los datos que nos pasan desde ThirdFragment
        val nombre = arguments?.getString("nombre") ?: ""
        val tipo = arguments?.getString("tipo") ?: ""
        val descripcion = arguments?.getString("descripcion") ?: ""
        val precio = arguments?.getDouble("precio") ?: 0.0

        // rellenamos la UI
        binding.tvDetalleNombre.text = nombre
        binding.tvDetalleTipo.text = tipo
        binding.tvDetallePrecio.text = "%.0f€".format(precio)

        binding.btnComprarVehiculo.setOnClickListener {
            // añadimos el vehículo al usuario
            val mainActivity = activity as MainActivity
            val vehiculo = Vehiculo(tipo, nombre, descripcion, precio)
            mainActivity.usuario?.agregarVehiculo(vehiculo)

            // volvemos al primer fragmento limpiando el backstack
            findNavController().popBackStack(R.id.FirstFragment, false)

            // mostramos toast con el vehículo comprado
            Toast.makeText(requireContext(), "Has comprado: $nombre", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}