package com.example.juegonaves.controller

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.juegonaves.view.GameView

class GameActivity : AppCompatActivity() {
    private lateinit var gameView: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()

        val nombreJugador = intent.getStringExtra("NOMBRE_JUGADOR") ?: "Jugador"
        val dificultad = intent.getStringExtra("DIFICULTAD") ?: "Medio"

        gameView = GameView(this, nombreJugador, dificultad)
        setContentView(gameView)
    }

    override fun onPause() {
        super.onPause()
        gameView.pause()
    }

    override fun onResume() {
        super.onResume()
        gameView.resume()
    }
}