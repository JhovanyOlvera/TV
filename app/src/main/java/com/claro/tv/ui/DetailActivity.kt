package com.claro.tv.ui

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.claro.tv.R
import com.claro.tv.adapter.CastAdapter
import com.claro.tv.api.IWSResource
import com.claro.tv.interfaces.IDetailView
import com.claro.tv.model.pojo.Cast
import com.claro.tv.model.pojo.ShowDetails
import com.claro.tv.utils.BaseApplication
import com.claro.tv.utils.Constants.KEY.KEY_DETAIL_ID
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject


class DetailActivity : AppCompatActivity(), IDetailView {

    @Inject
    lateinit var client: IWSResource

    lateinit var vm: DetailVM
    lateinit var adapterCast: CastAdapter
    lateinit var bar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = when {
            resources.getBoolean(R.bool.portrait_only) -> {ActivityInfo.SCREEN_ORIENTATION_PORTRAIT}
            else -> {ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE}
        }
        setContentView(R.layout.activity_detail)
        vm = ViewModelProvider(this@DetailActivity).get(DetailVM::class.java)

        bar = supportActionBar!!
        bar.hide()
        setupDagger()
        setupComponent()
    }
    /**
     * Método que inicializa los componentes.
     */
    fun setupComponent() {
        setupRecyclerView()
        vm.iDetailView = this
        vm.idShow = intent.getLongExtra(KEY_DETAIL_ID, 0)
        if (vm.showDetails == null) {
            vm.searchShowDetail(client)
        } else {
            showDetails(vm.showDetails!!)
            showCast(vm.cast!!)
        }

    }
    /**
     * Método que inicializa el RecyclerView.
     */
    private fun setupRecyclerView() {
        adapterCast = CastAdapter()
        rv_cast.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        rv_cast.setHasFixedSize(true)
        rv_cast.adapter = adapterCast
    }
    /**
     * Método que inicializa la inyeccción con dagger.
     */
    private fun setupDagger() {
        (application as BaseApplication).retrofitComponent.inject(this)
    }

    companion object {
        fun newIntent(ctx: Context, id: Long?): Intent {
            return Intent(ctx, DetailActivity::class.java).putExtra(KEY_DETAIL_ID, id)
        }
    }
    /**
     * Método que pinta los detalles en la interfaz
     * @param show     Detalle del Show
     */
    override fun showDetails(show: ShowDetails) {
        btn_link.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(show.officialSite)))
        }

        Glide.with(this).load(show.image?.get("medium"))
            .placeholder(R.drawable.blank_product)
            .into(iv_photo)

        iv_photo
        tv_name.text = show.name

        tv_net_name.visibility = View.GONE
        show.network?.let {
            tv_net_name.visibility = View.VISIBLE
            tv_net_name.text = it.name
        }

        tv_net_rating_average.visibility = View.GONE
        show.rating?.let {
            if(it.average != null) {
                tv_net_rating_average.visibility = View.VISIBLE
                tv_net_rating_average.text = "rating: ${it.average.toString()}"
            }
        }

        tv_sinop_desc.text = HtmlCompat.fromHtml(show.summary!!, HtmlCompat.FROM_HTML_MODE_LEGACY)
        tv_genere_desc.text = show.genres?.joinToString(", ")
        tv_horario_descr.text =
            "${show.schedule?.time} | ${show.schedule?.days?.joinToString(", ")}"
    }

    /**
     * Método que proporciona al RecyclerView la lista del elenco
     * @param cast     Lista del elenco
     */
    override fun showCast(cast: List<Cast>) {
        adapterCast.setData(cast)
    }
}
