package com.example.likedislikenotif
//file ini menampilkan penghitung "Like" dan "Dislike".
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), NotifActionListener {
//likeCount dan dislikeCount: Variabel global untuk menyimpan penghitung like dan dislike.
    companion object {
        var likeCount = 0
        var dislikeCount = 0
    }

    private lateinit var likeCounter: TextView
    private lateinit var dislikeCounter: TextView

    //onCreate: Menginisialisasi UI, menangani klik tombol untuk memicu notifikasi, dan memperbarui penghitung.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        likeCounter = findViewById(R.id.likeCount)
        dislikeCounter = findViewById(R.id.dislikeCount)
        val sendNotifButton: Button = findViewById(R.id.kirimNotif)

        // Check notification permission for Android 13+
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
            }
        }

        // Tampilkan notifikasi saat tombol diklik
        sendNotifButton.setOnClickListener {
            NotifService().showNotification(applicationContext)
        }

        // Perbarui tampilan awal
        updateCounters()
    }

    private fun updateCounters() {
        likeCounter.text = likeCount.toString()
        dislikeCounter.text = dislikeCount.toString()
    }

    // Implementasi aksi ketika notifikasi diklik
    override fun onLikeClicked() {
        likeCount++
        updateCounters()
        Toast.makeText(this, "Like added!", Toast.LENGTH_SHORT).show()
    }

    override fun onDislikeClicked() {
        dislikeCount++
        updateCounters()
        Toast.makeText(this, "Dislike added!", Toast.LENGTH_SHORT).show()
    }
}
