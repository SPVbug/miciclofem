package com.marcodiegopia.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIrRegistro: Button = findViewById(R.id.btn_IrRegistro)
        val btnIrInicioSesion: Button = findViewById(R.id.btn_IrInicioSesion)

        // Agregar un OnClickListener para el botón "Iniciar Sesión"
        btnIrInicioSesion.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
    }
}
