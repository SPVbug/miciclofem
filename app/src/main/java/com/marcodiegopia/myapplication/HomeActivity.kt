package com.marcodiegopia.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // direccion boton de cuenta
        val btnCuenta: Button = findViewById(R.id.btn_Cuenta)

        btnCuenta.setOnClickListener {

            val intent = Intent(this@HomeActivity, CuentaActivity::class.java)
            startActivity(intent)
        }


        // ir a PREMIUM
        val btnPremium: Button = findViewById(R.id.btn_Premium)

        btnPremium.setOnClickListener {
            val intent = Intent(this, PremiumActivity::class.java)
            startActivity(intent)

        }

        // ir a CALENDARIO
        val btnCalendario: Button = findViewById(R.id.btn_Calendario)

        btnCalendario.setOnClickListener {
            val intent = Intent(this, CalendarioActivity::class.java)
            startActivity(intent)

        }
    }
}