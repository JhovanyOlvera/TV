package com.claro.tv.model.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ShowTV(
    @SerializedName("id") @Expose var id : Long? = null,
    @SerializedName("name") @Expose var name : String? = null,
    @SerializedName("airdate") @Expose var airdate : String? = null,
    @SerializedName("airtime") @Expose var airtime : String? = null,
    @SerializedName("show") @Expose var show : Show? = null
)