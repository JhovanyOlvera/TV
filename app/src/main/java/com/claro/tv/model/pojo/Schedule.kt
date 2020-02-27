package com.claro.tv.model.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap

data class Schedule(
    @SerializedName("time") @Expose var time : String? = null,
    @SerializedName("days") @Expose var days : ArrayList<String>? = null
)