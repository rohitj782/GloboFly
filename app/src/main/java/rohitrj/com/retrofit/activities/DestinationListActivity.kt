package com.smartherd.globofly.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.smartherd.globofly.R
import com.smartherd.globofly.helpers.DestinationAdapter
import com.smartherd.globofly.helpers.SampleData
import com.smartherd.globofly.models.Destination
import kotlinx.android.synthetic.main.activity_destiny_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rohitrj.com.retrofit.services.DestinationServices
import rohitrj.com.retrofit.services.ServiceBuilder

class DestinationListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener {
            val intent = Intent(this@DestinationListActivity, DestinationCreateActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        loadDestinations()
    }

    private fun loadDestinations() {

        // To be replaced by retrofit code

        val destinationService = ServiceBuilder.buildService(DestinationServices::class.java)

        val requestCall = destinationService.getDestinationList()

        requestCall.enqueue(object : Callback<List<Destination>> {
            override fun onFailure(call: Call<List<Destination>>, t: Throwable) {

                Toast.makeText(applicationContext,"Failed"+t,Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Destination>>, response: Response<List<Destination>>) {

                if (response.isSuccessful) {

                    val destinationList = response.body()!!
                    destiny_recycler_view.adapter = DestinationAdapter(destinationList)


                }else{
                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                }
            }


        })

    }
}

