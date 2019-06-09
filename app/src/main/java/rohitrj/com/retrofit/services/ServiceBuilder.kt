package rohitrj.com.retrofit.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    //url to the server where it is hosted
    //below  is the default url for android emulator
    private const val URL="http://10.0.2.2:9000/"

    //creating okHttp clinet
    private val okHttp=OkHttpClient.Builder()

    //create retrofit builder
    private val builder=Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    //create retrofit instance
    private val retrofit= builder.build()

    fun<T> buildService(serviceType:Class<T>):T{
        return retrofit.create(serviceType)
    }
}