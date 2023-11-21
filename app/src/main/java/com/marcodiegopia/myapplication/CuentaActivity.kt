package com.marcodiegopia.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class CuentaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuenta)

        // Volver a la pantalla de inicio
        val btnVolver: Button = findViewById(R.id.btn_Volver)

        btnVolver.setOnClickListener {
            finish()
        }

        // Ir a inicio de sesion
        val btnIniciarSesion: Button = findViewById(R.id.btn_IniciarSesion)

        btnIniciarSesion.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Ir a registro
        val btnRegistrarse: Button = findViewById(R.id.btn_registrarse)

        btnRegistrarse.setOnClickListener {

            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
    }
}
