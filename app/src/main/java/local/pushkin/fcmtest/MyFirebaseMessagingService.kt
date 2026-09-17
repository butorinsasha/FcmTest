package local.pushkin.fcmtest

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

//POST https://fcm.googleapis.com/v1/projects/fcmtest-857a9/messages:send
//Authorization: ...
//{
//    "validate_only": false,
//    "message": {
//        "token": "diiShIw4S-eS4eqQkpU9sS:APA91bEfIbfqnQsGOiWVmjrnL1w_5tv1-c-TOQMuZ3ukIKCOKvj1Fsz2ivOU79U74i-rKmVB2-W8WaS9KVl3YUvYnDROWlyamhFUuxSTS6E-tdNnfGMsBHc",
//        "data": {
//            "title": "Deep Link",
//            "body": "Открыть SecondActivity",
//            "deep_link": "fcmtest://second"
//        }
//    }
//}

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        const val CHANNEL_ID = "fcm"
    }

    override fun onMessageReceived(message: RemoteMessage) {


        // 1. Получаем deep link из FCM data
        val deepLink = message.data["deep_link"] ?: return

        // 2. Создаём Intent для deep link
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(deepLink)
        )

        // 3. Заворачиваем Intent в PendingIntent
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        // 4. Создаём Notification Channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "FCM notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }

        // 5. Создаём notification
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(message.data["title"])
            .setContentText(message.data["body"])
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this)
            .notify(1, notification)
    }
}