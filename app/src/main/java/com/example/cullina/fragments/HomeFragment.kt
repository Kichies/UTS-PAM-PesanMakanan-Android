package com.example.cullina.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cullina.R
import com.example.cullina.models.Food
import com.example.cullina.models.Order

class HomeFragment : Fragment() {

    // Daftar 10 makanan - SEMUA akan ditampilkan
    private val foodList = listOf(
        Food(1, "Donut", "Donat klasik dengan topping gula.", R.drawable.donut_circle),
        Food(2, "Ice Cream Sandwich", "Es krim vanila diapit dua biskuit coklat.", R.drawable.icecream_circle),
        Food(3, "Froyo", "Yogurt beku premium dengan topping buah segar.", R.drawable.froyo_circle),
        Food(4, "Milkshake Stroberi", "Milkshake stroberi kental dengan whipped cream.", R.drawable.milkshake_circle),
        Food(5, "Kentang Goreng", "Kentang goreng renyah disajikan dengan saus tomat.", R.drawable.frenchfires_circle),
        Food(6, "Cheeseburger Klasik", "Burger daging sapi premium dengan keju cheddar leleh.", R.drawable.burger_circle),
        Food(7, "Pizza Pepperoni", "Pizza klasik dengan topping pepperoni dan keju mozzarella.", R.drawable.pizza_circle),
        Food(8, "Hotdog Sosis", "Hotdog klasik dengan sosis panggang dan saus mustard.", R.drawable.hotdog_circle),
        Food(9, "Muffin Coklat", "Muffin coklat lembut dengan taburan choco chips.", R.drawable.muffin_circle),
        Food(10, "Pancake Maple Syrup", "Tumpukan pancake lembut disiram sirup maple.", R.drawable.pancake_circle)

    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val layoutFoods = view.findViewById<LinearLayout>(R.id.layoutFoods)

        // Set nama user dari SharedPreferences
        val tvHello = view.findViewById<TextView>(R.id.tvHello)
        val sharedPreferences = requireContext().getSharedPreferences("user_prefs", android.content.Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "User") ?: "User"
        tvHello.text = "Halo $username,"

        // Hapus semua view sebelumnya (jika ada)
        layoutFoods.removeAllViews()

        // Populate SEMUA 10 makanan
        foodList.forEach { food ->
            val foodView = inflater.inflate(R.layout.item_food, layoutFoods, false)

            val imageView = foodView.findViewById<ImageView>(R.id.ivFood)
            val tvName = foodView.findViewById<TextView>(R.id.tvFoodName)
            val tvDesc = foodView.findViewById<TextView>(R.id.tvFoodDesc)

            // Set data untuk setiap makanan
            imageView.setImageResource(food.imageRes)
            tvName.text = food.name
            tvDesc.text = food.description

            // Ketika item makanan di-klik, tambahkan ke pesanan
            foodView.setOnClickListener {
                Order.addFood(food)
                Toast.makeText(requireContext(), "${food.name} ditambahkan ke pesanan", Toast.LENGTH_SHORT).show()
            }

            layoutFoods.addView(foodView)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        // Refresh tampilan jika ada perubahan
        val layoutFoods = view?.findViewById<LinearLayout>(R.id.layoutFoods)
        if (layoutFoods != null) {
            // Jika perlu refresh data, bisa dilakukan di sini
        }
    }
}