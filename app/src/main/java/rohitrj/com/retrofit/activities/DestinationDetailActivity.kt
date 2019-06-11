package com.smartherd.globofly.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.smartherd.globofly.R
import com.smartherd.globofly.helpers.SampleData
import com.smartherd.globofly.models.Destination
import kotlinx.android.synthetic.main.activity_destiny_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rohitrj.com.retrofit.services.DestinationServices
import rohitrj.com.retrofit.services.ServiceBuilder


class DestinationDetailActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_destiny_detail)

		setSupportActionBar(detail_toolbar)
		// Show the Up button in the action bar.
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		val bundle: Bundle? = intent.extras

		if (bundle?.containsKey(ARG_ITEM_ID)!!) {

			val id = intent.getIntExtra(ARG_ITEM_ID, 0)

			loadDetails(id)

			initUpdateButton(id)

			initDeleteButton(id)
		}
	}

	private fun loadDetails(id: Int) {


		val services=ServiceBuilder.buildService(DestinationServices::class.java)
		val requestCall=services.getDestination(id)

		requestCall.enqueue(object: Callback<Destination>{
			override fun onFailure(call: Call<Destination>, t: Throwable) {

			}

			override fun onResponse(call: Call<Destination>, response: Response<Destination>) {

				val destination=response.body()
		destination?.let {
			et_city.setText(destination.city)
			et_description.setText(destination.description)
			et_country.setText(destination.country)

			collapsing_toolbar.title = destination.city
		}

			}

		})

	}

	private fun initUpdateButton(id: Int) {

		btn_update.setOnClickListener {

			val city = et_city.text.toString()
			val description = et_description.text.toString()
			val country = et_country.text.toString()


            val destination = Destination()
            destination.id = id
            destination.city = city
            destination.description = description
            destination.country = country


			val services = ServiceBuilder.buildService(DestinationServices::class.java)
			val call = services.updateDestination(id,destination)

			call.enqueue(object: Callback<Destination>{
				override fun onFailure(call: Call<Destination>, t: Throwable) {
					Toast.makeText(this@DestinationDetailActivity,"Failed",Toast.LENGTH_LONG).show()
				}

				override fun onResponse(call: Call<Destination>, response: Response<Destination>) {

					Toast.makeText(this@DestinationDetailActivity,"Successfully Updated!",Toast.LENGTH_LONG).show()
					finish()
				}

			})
 // Move back to DestinationListActivity

		}
	}

	private fun initDeleteButton(id: Int) {

		btn_delete.setOnClickListener {


			var services = ServiceBuilder.buildService(DestinationServices::class.java)

			var call = services.deleteDestination(id)

			call.enqueue(object : Callback<Unit>{
				override fun onFailure(call: Call<Unit>, t: Throwable) {
					Toast.makeText(this@DestinationDetailActivity,"Failed",Toast.LENGTH_LONG).show()
				}

				override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
					Toast.makeText(this@DestinationDetailActivity,"Successfully Updated!",Toast.LENGTH_LONG).show()
					finish()
				}

			})

		}
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		val id = item.itemId
		if (id == android.R.id.home) {
			navigateUpTo(Intent(this, DestinationListActivity::class.java))
			return true
		}
		return super.onOptionsItemSelected(item)
	}

	companion object {

		const val ARG_ITEM_ID = "item_id"
	}
}
