package com.claro.tv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.claro.tv.R
import com.claro.tv.model.pojo.Cast
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.item_cast.view.*

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {
    private val listCast: MutableList<Cast> = mutableListOf()

    fun setData(data: List<Cast>?) {
        this.listCast.clear()
        data?.let { this.listCast.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return this.listCast.size
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bindHistoricalAdvance(listCast[position])
    }

    class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindHistoricalAdvance(show: Cast) {

            Glide.with(itemView.context).load(show.person?.image?.get("medium"))
                .placeholder(R.drawable.blank_product)
                .into(itemView.iv_cast)

            itemView.tv_nameCast.text = show.person?.name

        }

    }

}