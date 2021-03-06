package com.claro.tv.model.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap

data class Network(
    @SerializedName("id") @Expose var id : Long? = null,
    @SerializedName("name") @Expose var name : String? = null
)