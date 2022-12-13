package com.example.simpkd.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.simpkd.R
import com.example.simpkd.activities.*
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


val intentMaps = hashMapOf<String, Class<*>>(
    Pair("news", NewsActivity::class.java),
    Pair("letterOfResponsibility", LetterOfResponsibilityActivity::class.java),
    Pair("vehicleMaintenance", VehicleMaintenanceActivity::class.java),
    Pair("letterOfTask", LetterOfTaskActivity::class.java)
)

class FirebaseNotificationService: FirebaseMessagingService() {

    @Suppress("DEPRECATION")
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        val data = p0.data
        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager
        val soundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val intent = Intent(this, intentMaps.getOrDefault(data["notification_intent"],
            MainActivity::class.java))
        val pendingIntent = PendingIntent.getActivity(this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel("Notification", "Notification",
                NotificationManager.IMPORTANCE_HIGH)

            val att = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
            notificationChannel.setSound(soundUri, att)
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC

            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notification = NotificationCompat.Builder(this, "Notification").apply {
            setSmallIcon(R.drawable.logo_dinas__provinsi_bali)
            setContentTitle(data["notification_title"])
            setContentText(data["notification_message"])
            setStyle(NotificationCompat.BigTextStyle().bigText(data["notification_message"]))
            setSound(soundUri)
            setContentIntent(pendingIntent)
            setCategory(NotificationCompat.CATEGORY_SOCIAL)
            setAutoCancel(true)
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            color = ContextCompat.getColor(this@FirebaseNotificationService,
                R.color.primaryColor)
            priority = NotificationCompat.PRIORITY_HIGH
        }.build()
        notificationManager.notify(0, notification)

        val powerManager: PowerManager = getSystemService(Context.POWER_SERVICE) as PowerManager

        if (!powerManager.isInteractive) {
            powerManager.newWakeLock(
                PowerManager.FULL_WAKE_LOCK
                        or PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.ON_AFTER_RELEASE,
                "SIMPKD:notificationWakeLock")
                .acquire(5000)
        }
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)

        Log.e("TOKEN", p0)
    }

}