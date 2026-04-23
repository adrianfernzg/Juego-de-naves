package com.example.juegonaves.controller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.juegonaves.R
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainLayout = findViewById<LinearLayout>(R.id.mainLayout)
        val etNombreJugador = findViewById<TextInputEditText>(R.id.etNombreJugador)
        val spinnerDificultad = findViewById<MaterialAutoCompleteTextView>(R.id.spinnerDificultad)
        val btnIniciar = findViewById<Button>(R.id.btnIniciar)

        val dificultades = arrayOf("Fácil", "Medio", "Difícil")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, dificultades)
        spinnerDificultad.setAdapter(adapter)
        spinnerDificultad.setText("Medio", false)

        btnIniciar.setOnClickListener {
            val nombreJugador = etNombreJugador.text.toString().trim()
            val dificultadSeleccionada = spinnerDificultad.text.toString()

            if (nombreJugador.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa tu nombre", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("NOMBRE_JUGADOR", nombreJugador)
            intent.putExtra("DIFICULTAD", dificultadSeleccionada)
            startActivity(intent)
        }

        mainLayout.setOnClickListener {
            hideKeyboard()
            etNombreJugador.clearFocus()
        }

        etNombreJugador.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                etNombreJugador.clearFocus()
                true
            } else {
                false
            }
        }

        spinnerDificultad.setOnClickListener {
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}