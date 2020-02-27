package com.claro.tv.model.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ShowTVQuery(
    @SerializedName("score") @Expose var score : Double? = null,
    @SerializedName("show") @Expose var show : Show? = null
)