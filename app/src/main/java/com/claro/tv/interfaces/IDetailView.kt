package com.claro.tv.interfaces

import com.claro.tv.model.pojo.Cast
import com.claro.tv.model.pojo.ShowDetails

interface IDetailView {

    fun showDetails(show: ShowDetails)
    fun showCast(cast: List<Cast>)


}