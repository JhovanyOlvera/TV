package com.claro.tv.repository

import com.claro.tv.api.IWSResource
import com.claro.tv.interfaces.IShowDetailView
import com.claro.tv.interfaces.IShowTVView
import com.claro.tv.model.pojo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RestHttp {

    /**
     * Método que invoca el WS getShowTVbyDate
     * @param iView          Objeto de tipo {@link IShowTVView}.
     * @param client         Objeto de tipo {@link IWSResource}.
     * @param country        Codigo de la región.
     * @param date           Fecha actual.
     */
    fun searchShowTVbyDate(iView: IShowTVView, client: IWSResource, country: String, date: String) {
        client.getShowTVbyDate(country, date).enqueue(object : Callback<List<ShowTV>> {
            override fun onFailure(call: Call<List<ShowTV>>, t: Throwable) {
                t.stackTrace
                iView.error()
            }

            override fun onResponse(call: Call<List<ShowTV>>, response: Response<List<ShowTV>>) {
                iView.showProgram(response.body())
            }


        })
    }

    /**
     * Método que invoca el WS getShowTVbyQuery
     * @param iView          Objeto de tipo {@link IShowTVView}.
     * @param client         Objeto de tipo {@link IWSResource}.
     * @param query          Paramentro de busqueda.
     */
    fun searchShowTVbyQuery(iView: IShowTVView, client: IWSResource, query: String) {
        client.getShowTVbyQuery(query).enqueue(object : Callback<List<ShowTVQuery>> {
            override fun onFailure(call: Call<List<ShowTVQuery>>, t: Throwable) {
                t.stackTrace
            }

            override fun onResponse(
                call: Call<List<ShowTVQuery>>,
                response: Response<List<ShowTVQuery>>
            ) {
                var listShow = mutableListOf<Show>()
                for (a in response.body()!!) {
                    a.show?.let {
                        listShow.add(it)
                    }
                }

                iView.showProgramQuery(listShow)
            }


        })
    }

    /**
     * Método que invoca el WS getShowTVbyKey
     * @param iView          Objeto de tipo {@link IShowDetailView}.
     * @param client         Objeto de tipo {@link IWSResource}.
     * @param id             Id del ShowTV.
     */
    fun searchShowDetail(iView: IShowDetailView, client: IWSResource, id: String) {
        client.getShowTVbyKey(id).enqueue(object : Callback<ShowDetails> {
            override fun onFailure(call: Call<ShowDetails>, t: Throwable) {
                t.stackTrace
            }

            override fun onResponse(call: Call<ShowDetails>, response: Response<ShowDetails>) {
                iView.showDetails(response.body()!!)
                searchShowDetailCast(iView, client, id)
            }


        })

    }

    /**
     * Método que invoca el WS getShowTVAndCastByKey
     * @param iView          Objeto de tipo {@link IShowDetailView}.
     * @param client         Objeto de tipo {@link IWSResource}.
     * @param id             Id del ShowTV.
     */
    fun searchShowDetailCast(iView: IShowDetailView, client: IWSResource, id: String) {
        client.getShowTVAndCastByKey(id).enqueue(object : Callback<List<Cast>> {
            override fun onFailure(call: Call<List<Cast>>, t: Throwable) {
                t.stackTrace
            }

            override fun onResponse(call: Call<List<Cast>>, response: Response<List<Cast>>) {
                iView.showCast(response.body()!!)
            }

        })
    }

}