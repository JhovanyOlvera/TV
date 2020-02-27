package com.claro.tv.interfaces

import com.claro.tv.model.pojo.Cast
import com.claro.tv.model.pojo.Show
import com.claro.tv.model.pojo.ShowDetails
import com.claro.tv.model.pojo.ShowTV

interface IShowDetailView {

    fun error()

    fun showDetails(show: ShowDetails)

    fun showCast(cast: List<Cast>)
}