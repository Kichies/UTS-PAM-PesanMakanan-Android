package com.example.cullina

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class OrderSuccessActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_success)

        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        val tvHello = findViewById<TextView>(R.id.tvHello)
        val btnKembali = findViewById<Button>(R.id.btnKembali)

        val username = sharedPreferences.getString("username", "User") ?: "User"
        tvHello.text = getString(R.string.halo_user, username)

        btnKembali.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }
}