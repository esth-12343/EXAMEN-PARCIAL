package com.example.juegodecolores_examenfinal

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ResultFragment : Fragment() {

    private lateinit var scoreText: TextView
    private lateinit var highScoreText: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ScoreAdapter
    private val scoreHistory = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflamos la vista del fragmento
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        // Referencias a los elementos del layout
        scoreText = view.findViewById(R.id.scoreText)
        highScoreText = view.findViewById(R.id.highScoreText)
        recyclerView = view.findViewById(R.id.recyclerView)
        val btnReplay: Button = view.findViewById(R.id.btnReplay)

        // Obtener el puntaje actual desde los argumentos
        val currentScore = arguments?.getInt("score") ?: 0
        scoreText.text = "Tu puntaje: $currentScore"

        // Guardar y mostrar el récord más alto usando SharedPreferences
        val prefs = requireContext().getSharedPreferences("ColorGame", Context.MODE_PRIVATE)
        val highScore = prefs.getInt("highScore", 0)

        if (currentScore > highScore) {
            prefs.edit().putInt("highScore", currentScore).apply()
            highScoreText.text = "Nuevo récord: $currentScore 🎉"
        } else {
            highScoreText.text = "Puntaje más alto: $highScore"
        }

        // Mostrar historial de puntajes
        scoreHistory.add(currentScore)
        adapter = ScoreAdapter(scoreHistory)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Botón para volver a jugar
        btnReplay.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_gameFragment)
        }

        return view
    }
}
