package com.marcodiegopia.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class CalendarioActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var startDate: Date
    private lateinit var endDate: Date
    private val highlightedDates = HashSet<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendario)

        val startDateMillis = intent.getLongExtra("start_date", 0)
        val endDateMillis = intent.getLongExtra("end_date", 0)
        startDate = Date(startDateMillis)
        endDate = Date(endDateMillis)

        // Crear un conjunto de fechas en el rango
        val currentDate = Calendar.getInstance().apply {
            time = startDate
        }
        while (currentDate.timeInMillis <= endDate.time) {
            highlightedDates.add(currentDate.timeInMillis)
            currentDate.add(Calendar.DAY_OF_MONTH, 1)
        }

        calendarView = findViewById(R.id.calendarView)
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)

            // Verificar si la fecha seleccionada está en el rango
            if (selectedDate.timeInMillis in highlightedDates) {
                // La fecha seleccionada está dentro del rango, puedes cambiar su apariencia aquí
                val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                println("Fecha seleccionada: $formattedDate está dentro del rango.")
            } else {
                // La fecha seleccionada no está dentro del rango, puedes manejarlo aquí
                Toast.makeText(this, "Seleccione una fecha dentro del rango", Toast.LENGTH_SHORT).show()
            }
        }

        // Marcar las fechas en el calendario
        marcarFechasEnCalendario()

        // Ir a registrar periodo en calendario
        val btnRegistrarFechas: Button = findViewById(R.id.btn_RegistrarFechas)
        btnRegistrarFechas.setOnClickListener {
            val intent = Intent(this, RegistrarFechasActivity::class.java)
            startActivity(intent)
        }
    }

    // Método para marcar las fechas en el calendario
    private fun marcarFechasEnCalendario() {
        for (dateInMillis in highlightedDates) {
            calendarView.setDate(dateInMillis, true, false)
        }
    }
}
