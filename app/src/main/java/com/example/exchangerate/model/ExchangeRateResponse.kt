package com.example.exchangerate.model

import com.google.gson.annotations.SerializedName

data class ExchangeRateResponse(
    @SerializedName("base_code") val baseCode: String,
    @SerializedName("conversion_rates") val conversionRates: Map<String, Double>,
    @SerializedName("time_last_update_unix") val timeLastUpdateUnix: Long,
    @SerializedName("time_last_update_utc") val timeLastUpdateUtc: String,
    @SerializedName("time_next_update_unix") val timeNextUpdateUnix: Long,
    @SerializedName("time_next_update_utc") val timeNextUpdateUtc: String
)
