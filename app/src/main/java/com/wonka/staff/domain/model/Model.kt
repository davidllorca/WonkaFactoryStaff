package com.wonka.staff.domain.model

/**
 * Model classes of Domain layer.
 */

data class Worker(
        val id: Int = 0,
        val firstName: String = "",
        val lastName: String = "",
        val image: String = "",
        val profession: String = "",
        val height: Int = 0,
        val country: String = "",
        val age: Int = 0,
        val favorite: Favorite = Favorite(),
        val gender: String = "",
        val email: String = "")

data class WorkerDetail(
        val firstName: String = "",
        val lastName: String = "",
        val description: String = "",
        val quota: String = "",
        val image: String = "",
        val profession: String = "",
        val height: Int = 0,
        val country: String = "",
        val age: Int = 0,
        val favorite: Favorite = Favorite(),
        val gender: String = "",
        val email: String = "")

data class Favorite(
        val color: String = "",
        val food: String = "",
        val randomString: String = "",
        val song: String = "")