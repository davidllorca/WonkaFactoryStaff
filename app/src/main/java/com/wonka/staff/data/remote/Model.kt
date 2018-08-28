package com.wonka.staff.data.remote

import com.google.gson.annotations.SerializedName

/**
 * Model contract with remote server data.
 */
data class WorkerData(
        @SerializedName("first_name") val firstName: String = "",
        @SerializedName("last_name") val lastName: String = "",
        @SerializedName("favorite") val favorite: FavoriteData = FavoriteData(),
        @SerializedName("gender") val gender: String = "",
        @SerializedName("image") val image: String = "",
        @SerializedName("profession") val profession: String = "",
        @SerializedName("email") val email: String = "",
        @SerializedName("age") val age: Int = 0,
        @SerializedName("country") val country: String = "",
        @SerializedName("height") val height: Int = 0,
        @SerializedName("id") val id: Int = 0)

data class FavoriteData(
        @SerializedName("color") val color: String = "",
        @SerializedName("food") val food: String = "",
        @SerializedName("random_string") val randomString: String = "",
        @SerializedName("song") val song: String = "")


data class WorkerDetailData(
        @SerializedName("last_name") val lastName: String = "",
        @SerializedName("description") val description: String = "",
        @SerializedName("image") val image: String = "",
        @SerializedName("profession") val profession: String = "",
        @SerializedName("quota") val quota: String = "",
        @SerializedName("height") val height: Int = 0,
        @SerializedName("first_name") val firstName: String = "",
        @SerializedName("country") val country: String = "",
        @SerializedName("age") val age: Int = 0,
        @SerializedName("favorite") val favorite: FavoriteData = FavoriteData(),
        @SerializedName("gender") val gender: String = "",
        @SerializedName("email") val email: String = ""
)