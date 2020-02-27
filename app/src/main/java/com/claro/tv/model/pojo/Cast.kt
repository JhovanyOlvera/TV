package com.claro.tv.model.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap

data class Cast(
    @SerializedName("person") @Expose var person : Person? = null
)