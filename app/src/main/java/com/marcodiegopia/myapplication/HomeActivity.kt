package com.marcodiegopia.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



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

        // ir a NOTIFICACIONES
        val btnNotificaciones: Button = findViewById(R.id.btn_Notificaciones)

        btnNotificaciones.setOnClickListener {
            val intent = Intent(this, NotificacionesActivity::class.java)
            startActivity(intent)
        }

        // ir a PASTILLAS
        val btnPastillas: Button = findViewById(R.id.btn_Pastillas)

        btnPastillas.setOnClickListener {
            val intent = Intent(this, PastillasActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_desplegable, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.PrimerItem -> {
                // Redirigir a la actividad de Cuenta
                val intent = Intent(this, CuentaActivity::class.java)
                startActivity(intent)
            }
            R.id.SegundoItem -> {
                // Redirigir a la actividad de Notificaciones
                val intent = Intent(this, NotificacionesActivity::class.java)
                startActivity(intent)
            }
            R.id.TercerItem -> {
                // Redirigir a la actividad de Premium
                val intent = Intent(this, PremiumActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
