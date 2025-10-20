package com.example.juegodecolores_examenfinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        val btnStart = view.findViewById<Button>(R.id.btnStart)

        btnStart.setOnClickListener {
            showRules()
        }
        return view
    }

    private fun showRules() {
        AlertDialog.Builder(requireContext())
            .setTitle("OJO: Reglas del Juego")
            .setMessage("Presiona el botón que coincida con el color mostrado. " +
                    "Tienes 30 segundos para obtener la mayor cantidad de aciertos.")
            .setPositiveButton("Jugar") { _, _ ->
                findNavController().navigate(R.id.action_welcomeFragment_to_gameFragment)
            }
            .show()
    }
}
