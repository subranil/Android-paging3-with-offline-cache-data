package com.subranil.pagination.api

import com.google.gson.annotations.SerializedName
import com.subranil.pagination.model.Repo

data class SearchResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<Repo> = emptyList(),
    val nextPage: Int? = null
)
