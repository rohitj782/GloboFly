package rohitrj.com.retrofit.services

import com.smartherd.globofly.models.Destination
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DestinationServices {

    @GET("destination")
    fun getDestinationList():Call<List<Destination>>

    @GET("destination/{id}")
    fun getDestination(@Path("id")id:Int):Call<Destination>
}