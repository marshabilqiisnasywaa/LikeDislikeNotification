package com.example.likedislikenotif
//NotifService: Bertanggung jawab untuk membuat dan menampilkan notifikasi.
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap

class NotifService(private val listener: NotifActionListener? = null) {
//showNotification: Membuat dan menampilkan notifikasi dengan tombol "Like" dan "Dislike". Ini juga mengatur BigPictureStyle untuk menampilkan gambar di notifikasi.
    fun showNotification(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "like_dislike_channel"

        // membuat notif channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Like/Dislike Notifications", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        // Intent untuk Like
        val likeIntent = Intent(context, NotificationActionReceiver::class.java).apply {
            action = "ACTION_LIKE"
        }
        val likePendingIntent = PendingIntent.getBroadcast(context, 0, likeIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        // Intent untuk Dislike
        val dislikeIntent = Intent(context, NotificationActionReceiver::class.java).apply {
            action = "ACTION_DISLIKE"
        }
        val dislikePendingIntent = PendingIntent.getBroadcast(context, 1, dislikeIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        // Convert Drawable ke Bitmap untuk BigPictureStyle
        val drawable: Drawable? = ContextCompat.getDrawable(context, R.drawable.hanip)
        val bitmap: Bitmap = drawable?.toBitmap() ?: Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)

        // BigPictureStyle notification
        val bigPictureStyle = NotificationCompat.BigPictureStyle().bigPicture(bitmap)

        // membuat notif
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.tummy)
            .setContentTitle("Govannnnnnnnnnn gamtenk")
            .setStyle(bigPictureStyle)
            .addAction(R.drawable.likebutton, "Like", likePendingIntent)
            .addAction(R.drawable.dislikebutton, "Dislike", dislikePendingIntent)
            .setAutoCancel(true)
            .build()

        // memunculkan notif
        notificationManager.notify(0, notification)
    }
}
