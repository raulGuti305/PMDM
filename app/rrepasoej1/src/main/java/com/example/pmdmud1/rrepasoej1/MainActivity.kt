package com.example.pmdmud1.rrepasoej1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pmdmud1.rrepasoej1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO 1: identifica y conecta las vistas por su R.id.
        val et1 = binding.etNombre
        val et2 = binding.etMinutos
        val et3 = binding.etIntensidad

        val btnRegistrar = binding.btnRegistrar
        val btnLimpiar = binding.btnLimpiar

        val tvResultado = binding.tvResultado

        btnRegistrar.setOnClickListener {
            var nombreUsuario = et1.text.toString()
            var minutosUsuario = et2.text.toString().toIntOrNull()
            var intensidadUsuario = et3.text.toString().toIntOrNull()
            var mensaje : String = ""
            var registroCorrecto = true

            if (minutosUsuario == null ) {
                minutosUsuario = 0
            }

            if (intensidadUsuario == null) {
                intensidadUsuario = 0
            }

            if (nombreUsuario.isEmpty() || nombreUsuario.isBlank()) {
                mensaje = "No puedes introducir un registro con nombre vacío."
                registroCorrecto = false
            }

            if (minutosUsuario !in 1..3000) {
                mensaje = "Introduce un numero entre 1 y 300 para registrar una cantidad válida de minutos."
                registroCorrecto = false
            }

            if (intensidadUsuario in 0..10) {
                mensaje = when {
                    intensidadUsuario < 4 -> "(suave)"
                    intensidadUsuario < 7 -> "moderado"
                    else -> "(intenso)"
                }
            } else {
                mensaje = "introduce un numero entre 1 y 10 para registrar una intensidad válida."
                registroCorrecto = false
            }

            val puntuacion : Int = calcularPuntos(minutosUsuario, intensidadUsuario)

            if (registroCorrecto) {
                mensaje = nombreUsuario + " | " + minutosUsuario + " | " + intensidadUsuario + " | " + puntuacion
            }

            tvResultado.text = mensaje
        }

        btnLimpiar.setOnClickListener {
            et1.text.clear()
            et2.text.clear()
            et3.text.clear()
            tvResultado.setText(R.string.tvResultado)
        }
    }
    fun calcularPuntos (minutos : Int, intensidad : Int): Int {
        return minutos * intensidad
    }

}