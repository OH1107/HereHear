package com.ssafy.herehear.model.network.response

data class TempResponseItem(
    val author: String,
    val description: String,
    val id: Int,
    val img_url: String,
    val isbn13: String,
    val publisher: String,
    val stars_count: Int,
    val stars_sum: Int,
    val title: String
)