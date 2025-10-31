package com.example.cullina

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.cullina.fragments.HomeFragment
import com.example.cullina.fragments.OrderFragment
import com.example.cullina.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var tvHello: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        tvHello = findViewById(R.id.tvHello)

        val username = sharedPreferences.getString("username", "User") ?: "User"
        tvHello.text = "Halo $username,"

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val btnHome = findViewById<Button>(R.id.btnHome)
        val btnOrder = findViewById<Button>(R.id.btnOrder)
        val btnProfile = findViewById<Button>(R.id.btnProfile)

        btnHome.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        btnOrder.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, OrderFragment())
                .commit()
        }

        btnProfile.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileFragment())
                .commit()
        }
    }
}