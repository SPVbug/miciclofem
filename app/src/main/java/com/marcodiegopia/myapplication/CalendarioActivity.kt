package com.marcodiegopia.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

data class FechaPeriodo(val startingDate: String, val endingDate: String)

class CalendarioActivity : AppCompatActivity() {
    private lateinit var startingDate: TextView
    private lateinit var endingDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendario)

        // Volver a la pantalla de inicio
        val btnVolver: Button = findViewById(R.id.btn_Volver)

        btnVolver.setOnClickListener {
            finish()
        }

        startingDate = findViewById(R.id.startingDate)
        endingDate = findViewById(R.id.endingDate)

        val button: MaterialButton = findViewById(R.id.rangePicker)
        val btnEliminar: Button = findViewById(R.id.btnEliminar)
        val btnGuardar: Button = findViewById(R.id.btnGuardar)

        button.setOnClickListener {
            val materialDatePicker = MaterialDatePicker.Builder.dateRangePicker()
                .setSelection(androidx.core.util.Pair(
                    MaterialDatePicker.thisMonthInUtcMilliseconds(),
                    MaterialDatePicker.todayInUtcMilliseconds()
                ))
                .build()

            materialDatePicker.addOnPositiveButtonClickListener(
                MaterialPickerOnPositiveButtonClickListener { selection ->
                    val date1Millis = selection.first ?: 0
                    val date2Millis = selection.second ?: 0

                    val fechaPeriodo = FechaPeriodo(
                        formatDateToString(date1Millis),
                        formatDateToString(date2Millis)
                    )

                    startingDate.text = getString(R.string.selected_starting_date, fechaPeriodo.startingDate)
                    endingDate.text = getString(R.string.selected_ending_date, fechaPeriodo.endingDate)
                })

            materialDatePicker.show(supportFragmentManager, "tag")
        }

        // Agregar lógica para eliminar el registro cuando se presiona el botón "X"
        btnEliminar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                eliminarRegistro()
            }
        })

        // Agregar lógica para guardar las fechas cuando se presiona el botón "Guardar"
        btnGuardar.setOnClickListener {
            val date1 = startingDate.text.toString()
            val date2 = endingDate.text.toString()

            if (date1.isNotEmpty() && date2.isNotEmpty()) {
                val fechaPeriodo = FechaPeriodo(date1, date2)
                // Guardar las fechas en Firebase Realtime Database en dos nodos diferentes
                guardarFechasEnFirebase("time1", fechaPeriodo)
                guardarFechasEnFirebase("time2", fechaPeriodo)
            }
        }
    }

    private fun formatDateToString(millis: Long): String {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return dateFormat.format(Date(millis))
    }

    private fun guardarFechasEnFirebase(nodeName: String, fechaPeriodo: FechaPeriodo) {
        val firebaseDatabase = FirebaseDatabase.getInstance()
        val reference = firebaseDatabase.getReference(nodeName)

        reference.setValue(fechaPeriodo)
            .addOnSuccessListener {
                // Manejar el éxito de la escritura en Firebase
            }
            .addOnFailureListener { e ->
                // Manejar errores en la escritura en Firebase
                e.printStackTrace()
            }
    }

    private fun eliminarRegistro() {
        // Agrega aquí la lógica para eliminar el registro de tu aplicación
        // Puedes modificar esta función según tus necesidades específicas
        startingDate.text = "Dia comienzo"
        endingDate.text = "Dia final"
    }
}
