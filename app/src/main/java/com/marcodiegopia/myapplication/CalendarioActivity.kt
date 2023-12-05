package com.marcodiegopia.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import java.text.SimpleDateFormat
import java.util.*

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

        button.setOnClickListener {
            val materialDatePicker = MaterialDatePicker.Builder.dateRangePicker()
                .setSelection(androidx.core.util.Pair(
                    MaterialDatePicker.thisMonthInUtcMilliseconds(),
                    MaterialDatePicker.todayInUtcMilliseconds()
                ))
                .build()

            materialDatePicker.addOnPositiveButtonClickListener(
                MaterialPickerOnPositiveButtonClickListener { selection ->
                    val date1 = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                        .format(Date(selection.first ?: 0))
                    val date2 = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                        .format(Date(selection.second ?: 0))

                    startingDate.text = getString(R.string.selected_starting_date, date1)
                    endingDate.text = getString(R.string.selected_ending_date, date2)
                })

            materialDatePicker.show(supportFragmentManager, "tag")
        }

        // Agregar lógica para eliminar el registro cuando se presiona el botón "X"
        btnEliminar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                eliminarRegistro()
            }
        })
    }

    private fun eliminarRegistro() {
        // Agrega aquí la lógica para eliminar el registro de tu aplicación
        // Puedes modificar esta función según tus necesidades específicas
        startingDate.text = "Dia comienzo"
        endingDate.text = "Dia final"
    }
}
