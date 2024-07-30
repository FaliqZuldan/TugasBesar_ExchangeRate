package com.example.exchangerate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExchangeRateAdapter(
    private val exchangeRates: List<Map.Entry<String, Double>>
) : RecyclerView.Adapter<ExchangeRateAdapter.ExchangeRateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeRateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exchange_rate, parent, false)
        return ExchangeRateViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExchangeRateViewHolder, position: Int) {
        val currentItem = exchangeRates[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = exchangeRates.size

    class ExchangeRateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvCurrencyCode: TextView = itemView.findViewById(R.id.tvCurrencyCode)
        private val tvExchangeRate: TextView = itemView.findViewById(R.id.tvExchangeRate)

        fun bind(exchangeRateEntry: Map.Entry<String, Double>) {
            tvCurrencyCode.text = exchangeRateEntry.key
            tvExchangeRate.text = exchangeRateEntry.value.toString()
        }
    }
}
