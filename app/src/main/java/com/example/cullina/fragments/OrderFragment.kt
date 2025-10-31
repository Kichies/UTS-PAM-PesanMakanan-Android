package com.example.cullina.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.cullina.AddressActivity
import com.example.cullina.R
import com.example.cullina.models.Order

class OrderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order, container, false)
        val layoutOrders = view.findViewById<LinearLayout>(R.id.layoutOrders)
        val btnKirim = view.findViewById<Button>(R.id.btnKirim)
        val tvEmpty = view.findViewById<TextView>(R.id.tvEmpty)

        updateOrderList(layoutOrders, tvEmpty)

        btnKirim.setOnClickListener {
            if (Order.selectedFoods.isNotEmpty()) {
                val intent = Intent(requireContext(), AddressActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Pilih makanan terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun updateOrderList(layoutOrders: LinearLayout, tvEmpty: TextView) {
        layoutOrders.removeAllViews()

        if (Order.selectedFoods.isEmpty()) {
            tvEmpty.visibility = View.VISIBLE
        } else {
            tvEmpty.visibility = View.GONE

            // Tampilkan setiap item pesanan
            Order.selectedFoods.forEach { food ->
                val orderView = layoutInflater.inflate(R.layout.item_order, layoutOrders, false)

                val imageView = orderView.findViewById<ImageView>(R.id.ivOrderFood)
                val tvFoodName = orderView.findViewById<TextView>(R.id.tvOrderFoodName)
                val tvFoodDesc = orderView.findViewById<TextView>(R.id.tvOrderFoodDesc)
                val btnRemove = orderView.findViewById<Button>(R.id.btnRemoveFromOrder)

                // Set data makanan
                imageView.setImageResource(food.imageRes)
                tvFoodName.text = food.name
                tvFoodDesc.text = food.description

                // Tombol hapus
                btnRemove.setOnClickListener {
                    Order.removeFood(food)
                    updateOrderList(layoutOrders, tvEmpty)
                    Toast.makeText(requireContext(), "${food.name} dihapus dari pesanan", Toast.LENGTH_SHORT).show()
                }

                layoutOrders.addView(orderView)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh list ketika kembali ke fragment ini
        val layoutOrders = view?.findViewById<LinearLayout>(R.id.layoutOrders)
        val tvEmpty = view?.findViewById<TextView>(R.id.tvEmpty)
        if (layoutOrders != null && tvEmpty != null) {
            updateOrderList(layoutOrders, tvEmpty)
        }
    }
}