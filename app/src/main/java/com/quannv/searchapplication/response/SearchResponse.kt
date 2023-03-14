package com.quannv.searchapplication.response

import com.google.gson.annotations.SerializedName

data class SearchResponse (
    @SerializedName("total_results")
    val totalResult: Long,
    @SerializedName("page")
    val page: Long,
    @SerializedName("per_page")
    val perPage: Long,
    @SerializedName("next_page")
    val nextPage: String,
    @SerializedName("photos")
    val photos: List<Photo>,
)

data class Photo(
    @SerializedName("id")
    val id: Long,
    @SerializedName("width")
    val width: Long,
    @SerializedName("height")
    val height: Long,
    @SerializedName("url")
    val url: String,
    @SerializedName("photographer")
    val photographer: String,
    @SerializedName("photographer_id")
    val photographerID: Long,
    @SerializedName("avg_color")
    val avgColor: String,
    @SerializedName("liked")
    val liked: Boolean,
    @SerializedName("alt")
    val alt: Boolean,
    @SerializedName("src")
    val src: ResultImage
)

data class ResultImage(
    @SerializedName("original")
    val original: String,
    @SerializedName("large2x")
    val large2x: String,
    @SerializedName("large")
    val large: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("portrait")
    val portrait: String,
    @SerializedName("landscape")
    val landscape: String,
    @SerializedName("tiny")
    val tiny: String
)