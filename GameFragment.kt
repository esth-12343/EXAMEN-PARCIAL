package com.example.juegodecolores_examenfinal

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class GameFragment : Fragment() {

    private lateinit var colorBox: TextView
    private lateinit var timerText: TextView
    private lateinit var scoreText: TextView
    private var score = 0
    private var timeLeft = 30
    private var correctColor = 0
    private lateinit var countdown: CountDownTimer
    private val colors = listOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        colorBox = view.findViewById(R.id.colorBox)
        timerText = view.findViewById(R.id.timerText)
        scoreText = view.findViewById(R.id.scoreText)

        val btnRed = view.findViewById<Button>(R.id.btnRed)
        val btnGreen = view.findViewById<Button>(R.id.btnGreen)
        val btnBlue = view.findViewById<Button>(R.id.btnBlue)
        val btnYellow = view.findViewById<Button>(R.id.btnYellow)

        val buttons = listOf(btnRed, btnGreen, btnBlue, btnYellow)

        startGame()

        buttons.forEach { button ->
            button.setOnClickListener {
                val colorPressed = when (button.id) {
                    R.id.btnRed -> Color.RED
                    R.id.btnGreen -> Color.GREEN
                    R.id.btnBlue -> Color.BLUE
                    else -> Color.YELLOW
                }
                checkAnswer(colorPressed)
            }
        }

        return view
    }

    private fun startGame() {
        score = 0
        scoreText.text = "Puntaje: $score"
        generateNewColor()
        countdown = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = (millisUntilFinished / 1000).toInt()
                timerText.text = "Tiempo: $timeLeft s"
            }

            override fun onFinish() {
                val action = GameFragmentDirections.actionGameFragmentToResultFragment(score)
                findNavController().navigate(action)
            }
        }.start()
    }

    private fun generateNewColor() {
        correctColor = colors.random()
        colorBox.setBackgroundColor(correctColor)
    }

    private fun checkAnswer(selected: Int) {
        if (selected == correctColor) {
            score++
            playSound(R.raw.correct)
        } else {
            playSound(R.raw.wrong)
        }
        scoreText.text = "Puntaje: $score"
        generateNewColor()
    }

    private fun playSound(sound: Int) {
        val media = MediaPlayer.create(requireContext(), sound)
        media.start()
    }
}
