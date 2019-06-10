package com.smartherd.globofly.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.smartherd.globofly.R
import com.smartherd.globofly.helpers.SampleData
import com.smartherd.globofly.models.Destination
import kotlinx.android.synthetic.main.activity_destiny_create.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rohitrj.com.retrofit.services.DestinationServices
import rohitrj.com.retrofit.services.ServiceBuilder

class DestinationCreateActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_destiny_create)

		setSupportActionBar(toolbar)
		val context = this

		// Show the Up button in the action bar.
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		btn_add.setOnClickListener {
			val newDestination = Destination()
			newDestination.city = et_city.text.toString()
			newDestination.description = et_description.text.toString()
			newDestination.country = et_country.text.toString()


			val destinationServices = ServiceBuilder.buildService(DestinationServices::class.java)
			val requestCall = destinationServices.addDestination(newDestination)

			requestCall.enqueue(object : Callback<Destination>{
				override fun onResponse(call: Call<Destination>, response: Response<Destination>) {

					Toast.makeText(this@DestinationCreateActivity,"Success",Toast.LENGTH_LONG).show()
					finish()
				}

				override fun onFailure(call: Call<Destination>, t: Throwable) {
					TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
				}

			})

		}
	}
}
