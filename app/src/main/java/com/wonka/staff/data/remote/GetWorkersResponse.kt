package com.wonka.staff.data.remote

import com.google.gson.annotations.SerializedName

data class GetWorkersResponse(
        @SerializedName("current") val current: Int = 0,
        @SerializedName("total") val total: Int = 0,
        @SerializedName("results") val results: List<WorkerData> = listOf())
