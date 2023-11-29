package com.marcodiegopia.myapplication

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*
class RegistrarFechasActivity : AppCompatActivity() {

    private lateinit var btnStartDate: Button
    private lateinit var btnEndDate: Button
    private lateinit var tvDateRange: TextView

    private lateinit var calendar: Calendar
    private lateinit var startDate: Date
    private lateinit var endDate: Date
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_fechas)
        btnStartDate = findViewById(R.id.btnStartDate)
        btnEndDate = findViewById(R.id.btnEndDate)
        tvDateRange = findViewById(R.id.tvDateRange)

        calendar = Calendar.getInstance()

        btnStartDate.setOnClickListener {
            showDatePickerDialog(true)
        }

        btnEndDate.setOnClickListener {
            showDatePickerDialog(false)
        }
    }

    private fun showDatePickerDialog(isStartDate: Boolean) {
        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                calendar.set(year, month, dayOfMonth)
                val selectedDate = calendar.time
                updateDate(isStartDate, selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun updateDate(isStartDate: Boolean, selectedDate: Date) {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(selectedDate)

        if (isStartDate) {
            startDate = selectedDate
            btnStartDate.text = "Comienzo del periodo: $formattedDate"
        } else {
            endDate = selectedDate
            btnEndDate.text = "Fin del periodo: $formattedDate"
        }

        if (::startDate.isInitialized && ::endDate.isInitialized) {
            val dateRangeFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val formattedStartDate = dateRangeFormat.format(startDate)
            val formattedEndDate = dateRangeFormat.format(endDate)
            tvDateRange.text = "Rango de fechas seleccionadas: $formattedStartDate al $formattedEndDate"

            val intent = Intent(this, CalendarioActivity::class.java)
            intent.putExtra("start_date", startDate.time)
            intent.putExtra("end_date", endDate.time)
            startActivity(intent)
        }




        // Regresar al calendario
        val btnConfirmarFechas: Button = findViewById(R.id.btn_ConfirmarFechas)

        btnConfirmarFechas.setOnClickListener {
            val intent = Intent(this, CalendarioActivity::class.java)
            startActivity(intent)

        }
    }
}
