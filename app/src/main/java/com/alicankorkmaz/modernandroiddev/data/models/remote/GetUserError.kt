package com.alicankorkmaz.modernandroiddev.data.models.remote

import com.google.gson.annotations.SerializedName

data class GetUserError(
    @SerializedName("message")
    val message: String,
    @SerializedName("documentation_url")
    val documentationUrl: String
)
