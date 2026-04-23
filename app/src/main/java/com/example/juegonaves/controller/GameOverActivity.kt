package com.example.juegonaves.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.juegonaves.R

class GameOverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val nombreJugador = intent.getStringExtra("NOMBRE_JUGADOR") ?: "Jugador"
        val score = intent.getIntExtra("SCORE", 0)
        val dificultad = intent.getStringExtra("DIFICULTAD") ?: "Medio"

        findViewById<TextView>(R.id.tvGameOver).text = "GAME OVER"
        findViewById<TextView>(R.id.tvNombre).text = "Jugador: $nombreJugador"
        findViewById<TextView>(R.id.tvScore).text = "Puntuación: $score"
        findViewById<TextView>(R.id.tvDificultad).text = "Dificultad: $dificultad"

        findViewById<Button>(R.id.btnReintentar).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.btnSalir).setOnClickListener {
            finishAffinity()
        }
    }
}