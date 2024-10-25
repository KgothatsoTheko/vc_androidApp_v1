package za.co.varsitycollege.st10092141.vc_app

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("get-events")
    fun getEvents(): Call<List<Event>>

    @GET("get-file/{id}")
    fun getFile(@Path("id") fileId: String): Call<ResponseBody>
}