package com.marcodiegopia.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ir a registroo
        val btnIrRegistro: Button = findViewById(R.id.btn_IrRegistro)

        btnIrRegistro.setOnClickListener {

            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }

        // Ir a inicio de sesion
        val btnIrInicioSesion: Button = findViewById(R.id.btn_IrInicioSesion)

        btnIrInicioSesion.setOnClickListener {

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }






    }
}