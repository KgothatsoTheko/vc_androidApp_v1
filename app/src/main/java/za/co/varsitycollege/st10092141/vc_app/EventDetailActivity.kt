package za.co.varsitycollege.st10092141.vc_app

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class EventDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_event_detail)
        val event = intent.getParcelableExtra<Event>("EVENT")!!

        // Set event details
        findViewById<TextView>(R.id.eventName).text = event.eventName
        findViewById<TextView>(R.id.eventDescription).text = event.eventDescription
        findViewById<TextView>(R.id.eventLocation).text = event.location
        findViewById<TextView>(R.id.eventDate).text = event.date
        findViewById<TextView>(R.id.eventTime).text = event.time

        // Load image using Glide
        val imageView = findViewById<ImageView>(R.id.eventImage)
        event.file?.let {
            val imageUrl = "https://vc-app-v1.vercel.app/api/get-file/${it.id}"
            Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.logo) // Optional placeholder image
                .into(imageView)
        }
    }
}