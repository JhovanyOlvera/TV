package com.claro.tv.ui

import androidx.lifecycle.ViewModel
import com.claro.tv.api.IWSResource
import com.claro.tv.interfaces.IDetailView
import com.claro.tv.interfaces.IShowDetailView
import com.claro.tv.model.pojo.Cast
import com.claro.tv.model.pojo.ShowDetails
import com.claro.tv.repository.RestHttp

class DetailVM : ViewModel(), IShowDetailView {

    var iView: IShowDetailView = this
    lateinit var iDetailView: IDetailView
    var idShow : Long = 0

     var showDetails: ShowDetails? = null
     var cast: List<Cast>? = null

    fun searchShowDetail(client: IWSResource) {
        RestHttp().searchShowDetail(iView, client, idShow.toString())
    }

    override fun error() {

    }

    override fun showDetails(show: ShowDetails) {
        showDetails = show
        iDetailView.showDetails(show)
    }

    override fun showCast(cast: List<Cast>) {
        this.cast = cast
        iDetailView.showCast(cast)
    }





}