package com.example.ejercicio4_diseo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio4_diseo.databinding.ItemVehiculoBinding
import com.example.ejercicio4_diseo.modelo.Vehiculo

class VehiculoAdapter(
    private val listaVehiculos: List<Vehiculo>,
    private val onItemClick: (Vehiculo) -> Unit
) : RecyclerView.Adapter<VehiculoAdapter.VehiculoViewHolder>() {

    inner class VehiculoViewHolder(private val binding: ItemVehiculoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(vehiculo: Vehiculo) {
            binding.tvNombreVehiculo.text = vehiculo.nombre
            binding.tvTipoVehiculo.text = vehiculo.tipo
            binding.tvPrecioVehiculo.text = "%.0f€".format(vehiculo.precio)

            // icono según el tipo
            binding.tvTipoIcono.text = if (vehiculo.tipo == "Coche") "🚗" else "🏍️"

            binding.root.setOnClickListener {
                onItemClick(vehiculo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculoViewHolder {
        val binding = ItemVehiculoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VehiculoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehiculoViewHolder, position: Int) {
        holder.bind(listaVehiculos[position])
    }

    override fun getItemCount() = listaVehiculos.size
}