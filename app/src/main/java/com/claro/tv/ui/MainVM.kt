package com.claro.tv.ui

import androidx.lifecycle.ViewModel
import com.claro.tv.api.IWSResource
import com.claro.tv.interfaces.IMainView
import com.claro.tv.interfaces.IShowTVView
import com.claro.tv.model.pojo.Show
import com.claro.tv.model.pojo.ShowTV
import com.claro.tv.repository.RestHttp
import java.text.SimpleDateFormat
import java.util.*


class MainVM : ViewModel(), IShowTVView {

    var iView: IShowTVView = this
    lateinit var iMainView: IMainView

    fun searchShowTVDefault(client: IWSResource) {
        val pattern = "yyyy-MM-dd"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date: String = simpleDateFormat.format(Date())
        RestHttp().searchShowTVbyDate(iView, client, "US", date)
    }

    fun searchShowTVQuery(client: IWSResource, query: String) {
        RestHttp().searchShowTVbyQuery(iView, client, query)
    }

    override fun error() {

    }

    override fun showProgram(listShowTV: List<ShowTV>?) {
        iMainView.showProgram(listShowTV)
    }

    override fun showProgramQuery(listShow: List<Show>?) {
        iMainView.showProgramQuery(listShow)
    }


}