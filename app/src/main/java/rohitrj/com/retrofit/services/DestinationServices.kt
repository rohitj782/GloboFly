package rohitrj.com.retrofit.services

import com.smartherd.globofly.models.Destination
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DestinationServices {

    //retriving data from the server
    @GET("destination")
    fun getDestinationList():Call<List<Destination>>

    //using path parameters
    @GET("destination/{id}")
    fun getDestination(@Path("id")id:Int):Call<Destination>

    //posting to the server
    @POST("destination")
    fun addDestination (@Body destination: Destination):Call<Destination>

}