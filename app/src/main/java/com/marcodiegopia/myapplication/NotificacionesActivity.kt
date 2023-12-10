package com.marcodiegopia.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Build
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificacionesActivity : AppCompatActivity() {

    companion object{
        const val MY_CHANNEL_ID = "myChannel"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificaciones)

        // Volver a la pantalla de inicio
        val btnVolver: Button = findViewById(R.id.btn_Volver)
        val myNotificationButton = findViewById<Button>(R.id.btnNotification)
        createChannel()
        myNotificationButton.setOnClickListener {
            createSimpleNotification()
        }

        btnVolver.setOnClickListener {
            finish()
        }

    }
    fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MY_CHANNEL_ID,
                "MySuperChannel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Suscribete"
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }
    fun createSimpleNotification(){
        var builder = NotificationCompat.Builder(this, MY_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_delete)
            .setContentTitle("My title")
            .setContentText("Esto es un ejemplo")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(1, builder.build())
        }
    }
}