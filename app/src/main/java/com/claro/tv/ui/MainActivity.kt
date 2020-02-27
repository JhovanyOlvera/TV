package com.claro.tv.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.claro.tv.R
import com.claro.tv.adapter.ShowAdapter
import com.claro.tv.adapter.ShowTVAdapter
import com.claro.tv.api.IWSResource
import com.claro.tv.interfaces.IMainView
import com.claro.tv.model.pojo.Show
import com.claro.tv.model.pojo.ShowTV
import com.claro.tv.utils.BaseApplication
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), IMainView {

    @Inject
    lateinit var client: IWSResource

    lateinit var iMainView: IMainView

    lateinit var vm: MainVM
    lateinit var adapterShowTV : ShowTVAdapter
    lateinit var adapterShow : ShowAdapter

    lateinit var bar : ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProvider(this@MainActivity).get(MainVM::class.java)
        bar = supportActionBar!!
        bar.title = "Program TV"
        setupDagger()
        setupComponent()
    }

    /**
     * Método que inicializa los componentes.
     */
    fun setupComponent(){
        vm.iMainView = this
        setupRecyclerView()
        setupRecyclerView2()
        vm.searchShowTVDefault(client)
    }
    /**
    * Método que inicializa el RecyclerViewDefault.
    */
    private fun setupRecyclerView() {
        adapterShowTV = ShowTVAdapter()
        rv_showTV.layoutManager = LinearLayoutManager(applicationContext)
        rv_showTV.setHasFixedSize(true)
        rv_showTV.adapter = adapterShowTV
    }
    /**
     * Método que inicializa el RecyclerViewQuery.
     */
    private fun setupRecyclerView2() {
        adapterShow = ShowAdapter()
        rv_show.layoutManager = LinearLayoutManager(applicationContext)
        rv_show.setHasFixedSize(true)
        rv_show.adapter = adapterShow
    }
    /**
     * Método que inicializa la inyeccción con dagger.
     */
    private fun setupDagger() {
        (application as BaseApplication).retrofitComponent.inject(this)
    }

    /**
     * Método que inicializa el componente SearchView
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object  : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("",false)
                searchItem.collapseActionView()
                query?.let {
                    bar.title = it
                    vm.searchShowTVQuery(client, it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
            return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        fun newIntent(ctx: Context): Intent {
            return Intent(ctx, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }

    /**
     * Método que inyecta la lista de ShowTv al RecycleViewDefault.
     * @param listShowTV     Lista de ShowTV.
     */
    override fun showProgram(listShowTV: List<ShowTV>?) {
        rv_show.visibility = View.GONE
        rv_showTV.visibility = View.VISIBLE
        adapterShowTV.setData(listShowTV)
    }
    /**
     * Método que inyecta la lista de ShowTv al RecycleViewQuery.
     * @param listShowTV     Lista de ShowTV.
     */
    override fun showProgramQuery(listShow: List<Show>?) {
        rv_showTV.visibility = View.GONE
        rv_show.visibility = View.VISIBLE
        adapterShow.setData(listShow)
    }
    /**
     * Método que limpia el componente de busqueda.
     */
    override fun onBackPressed() {
        bar.title = "Program TV"
        vm.searchShowTVDefault(client)
    }

}
