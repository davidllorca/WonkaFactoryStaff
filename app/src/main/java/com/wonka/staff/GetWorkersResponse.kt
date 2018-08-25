package com.wonka.staff

import com.google.gson.annotations.SerializedName

data class WorkerEntity(
        @SerializedName("id") val id: Int = 0,
        @SerializedName("first_name") val firstName: String = "",
        @SerializedName("last_name") val lastName: String = "",
        @SerializedName("description") val description: String = "",
        @SerializedName("quota") val quota: String = "",
        @SerializedName("image") val image: String = "",
        @SerializedName("profession") val profession: String = "",
        @SerializedName("height") val height: Int = 0,
        @SerializedName("country") val country: String = "",
        @SerializedName("age") val age: Int = 0,
        @SerializedName("favorite") val favorite: Favorite = Favorite(),
        @SerializedName("gender") val gender: String = "",
        @SerializedName("email") val email: String = "")

data class Favorite(
        @SerializedName("color") val color: String = "",
        @SerializedName("food") val food: String = "",
        @SerializedName("random_string") val randomString: String = "",
        @SerializedName("song") val song: String = "")

data class GetWorkersResponse(
        @SerializedName("current") val current: Int = 0,
        @SerializedName("total") val total: Int = 0,
        @SerializedName("results") val results: List<WorkerEntity> = listOf())