package com.example.cullina.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.cullina.R

class ProfileFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        sharedPreferences = requireContext().getSharedPreferences("user_prefs", android.content.Context.MODE_PRIVATE)

        val tvProfileUsername = view.findViewById<TextView>(R.id.tvProfileUsername)

        val fullName = sharedPreferences.getString("full_name", "User")
        val username = sharedPreferences.getString("username", "username")

        tvProfileUsername.text = username

        return view
    }
}