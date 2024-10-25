package za.co.varsitycollege.st10092141.vc_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Home : Fragment() {

    lateinit var eventAdapter: EventAdapter

    val retrofit = Retrofit.Builder()
        .baseUrl("https://vc-app-v1.vercel.app/api/") // Replace with your server URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout first
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) // Set layout manager

        // API call to get events
        apiService.getEvents().enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                val events = response.body() ?: return

                // Log the event count to make sure multiple events are returned
                Log.d("HomeFragment", "Number of events: ${events.size}")

                // Set adapter with events
                eventAdapter = EventAdapter(events) { selectedEvent ->
                    val intent = Intent(requireContext(), EventDetailActivity::class.java)
                    intent.putExtra("EVENT", selectedEvent)
                    startActivity(intent)
                }
                recyclerView.adapter = eventAdapter // Set the adapter after fetching data
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                // Handle failure, e.g., show a toast or log the error
            }
        })

        return rootView
    }
}
