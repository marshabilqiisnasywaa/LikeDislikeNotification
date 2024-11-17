package com.example.likedislikenotif
//Receiver: Mendengarkan pesan broadcast yang dipicu oleh aksi notifikasi (like atau dislike).
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationActionReceiver : BroadcastReceiver() {
//Meningkatkan penghitung like atau dislike saat menerima aksi notifikasi yang sesuai.
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            "ACTION_LIKE" -> {
                MainActivity.likeCount++
            }
            "ACTION_DISLIKE" -> {
                MainActivity.dislikeCount++
            }
        }
        // Notif MainActivity untuk update UI
        val mainActivityIntent = Intent(context, MainActivity::class.java)
        mainActivityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(mainActivityIntent)
    }
}
