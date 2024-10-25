package za.co.varsitycollege.st10092141.vc_app

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EventAdapter(private val events: List<Event>, private val onClick: (Event) -> Unit) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://vc-app-v1.vercel.app/api/") // Your server URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event)

        // Adding click listener to open event details
        holder.itemView.setOnClickListener { onClick(event) }

        // Load event image using Glide
        event.file?.let { file ->
            loadImage(file.id, holder.eventImage)
        }
    }

    override fun getItemCount(): Int = events.size

    private fun loadImage(fileId: String, imageView: ImageView) {
        // Use Glide to fetch and load the image from the URL directly
        val imageUrl = "https://vc-app-v1.vercel.app/api/get-file/$fileId"

        Glide.with(imageView.context)
            .load(imageUrl)
            .placeholder(R.drawable.logo) // Optional placeholder image
            .into(imageView)
    }

    class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val eventName: TextView = view.findViewById(R.id.eventName)
        val eventImage: ImageView = view.findViewById(R.id.eventImage)

        fun bind(event: Event) {
            eventName.text = event.eventName
        }
    }
}

