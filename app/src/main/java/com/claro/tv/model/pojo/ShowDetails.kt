package com.claro.tv.model.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap

data class ShowDetails(
    @SerializedName("id") @Expose var id : Long? = null,
    @SerializedName("image") @Expose var image : LinkedTreeMap<String,String>? = null,
    @SerializedName("name") @Expose var name : String? = null,
    @SerializedName("network") @Expose var network : Network? = null,
    @SerializedName("schedule") @Expose var schedule : Schedule? = null,
    @SerializedName("officialSite") @Expose var officialSite : String? = null,
    @SerializedName("genres") @Expose var genres : ArrayList<String>? = null,
    @SerializedName("rating") @Expose var rating : Rating? = null,
    @SerializedName("summary") @Expose var summary : String? = null


)