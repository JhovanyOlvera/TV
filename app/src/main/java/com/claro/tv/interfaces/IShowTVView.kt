package com.claro.tv.interfaces

import com.claro.tv.model.pojo.Show
import com.claro.tv.model.pojo.ShowTV

interface IShowTVView {

    fun error()

    fun showProgram(listShowTV : List<ShowTV>?)

    fun showProgramQuery(listShow : List<Show>?)
}