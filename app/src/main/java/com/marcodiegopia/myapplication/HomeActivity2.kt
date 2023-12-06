package com.marcodiegopia.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType {
    BASIC
}

class HomeActivity2 : AppCompatActivity() {
    private lateinit var emailTextView: TextView
    private lateinit var providerTextView: TextView
    private lateinit var btn_IrCerrarSesion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)

        emailTextView = findViewById(R.id.emailTextView) // Asegúrate de que estos IDs coincidan con los de tu layout XML
        providerTextView = findViewById(R.id.providerTextView)
        btn_IrCerrarSesion = findViewById(R.id.btn_IrCerrarSesion)

        // Setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")
    }

    private fun setup(email: String, provider: String) {
        title = "Inicio"
        emailTextView.text = email
        providerTextView.text = provider

        btn_IrCerrarSesion.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }
}
