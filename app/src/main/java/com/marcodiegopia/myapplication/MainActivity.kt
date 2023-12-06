package com.marcodiegopia.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var btnIrIniciarSesion: Button
    private lateinit var btn_IrRegistro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the email and password EditTexts
        emailEditText = findViewById(R.id.emailEdittext1)
        passwordEditText = findViewById(R.id.passwordEditText)
        btn_IrRegistro = findViewById(R.id.btn_IrRegistro)

        // Find the "Iniciar Sesión" button
        btnIrIniciarSesion = findViewById(R.id.btn_IrIniciarSesion)

        // Ir a registro


        // Setup
        setup()
    }

    private fun setup() {
        title = "Autenticacion"

        btn_IrRegistro.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        // Usuario autenticado exitosamente
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }
        btnIrIniciarSesion.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        // Usuario autenticado exitosamente
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }

    }
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error auteticando al usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun showHome(email: String, provider: ProviderType){

        val homeIntent = Intent (this, HomeActivity2::class.java).apply {
            putExtra("email",email)
            putExtra( "provider", provider.name)
        }
        startActivity(homeIntent)
    }
}
