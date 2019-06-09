package rohitrj.com.retrofit.services

import com.smartherd.globofly.models.Destination
import retrofit2.Call
import retrofit2.http.GET

interface DestinationServices {

    @GET("destination")
    fun getDestinationList():Call<List<Destination>>
}