package com.example.exchangerate

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerate.model.ExchangeRateResponse
import com.example.exchangerate.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var rvExchangeRates: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnRefresh: Button
    private lateinit var spinnerBaseCurrency: Spinner

    private val currencies = listOf("USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY", "SEK", "NZD")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvExchangeRates = findViewById(R.id.rvExchangeRates)
        progressBar = findViewById(R.id.progressBar)
        btnRefresh = findViewById(R.id.btnRefresh)
        spinnerBaseCurrency = findViewById(R.id.spinnerBaseCurrency)

        rvExchangeRates.layoutManager = LinearLayoutManager(this)
        rvExchangeRates.adapter = ExchangeRateAdapter(emptyList())

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerBaseCurrency.adapter = adapter

        spinnerBaseCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                fetchExchangeRates(currencies[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        btnRefresh.setOnClickListener {
            fetchExchangeRates(spinnerBaseCurrency.selectedItem.toString())
        }


        fetchExchangeRates(currencies[0])
    }

    private fun fetchExchangeRates(baseCurrency: String) {
        progressBar.visibility = View.VISIBLE

        RetrofitInstance.api.getExchangeRates(baseCurrency).enqueue(object : Callback<ExchangeRateResponse> {
            override fun onResponse(
                call: Call<ExchangeRateResponse>,
                response: Response<ExchangeRateResponse>
            ) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val exchangeRates = response.body()?.conversionRates?.entries?.toList()
                    exchangeRates?.let {
                        rvExchangeRates.adapter = ExchangeRateAdapter(it)
                    }
                }
            }

            override fun onFailure(call: Call<ExchangeRateResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
