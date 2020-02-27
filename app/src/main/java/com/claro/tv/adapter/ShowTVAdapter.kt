package com.claro.tv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.claro.tv.R
import com.claro.tv.model.pojo.ShowTV
import com.claro.tv.ui.DetailActivity
import kotlinx.android.synthetic.main.item_show_tv.view.*

class ShowTVAdapter : RecyclerView.Adapter<ShowTVAdapter.ShowTVViewHolder>() {
    private val listShowTV: MutableList<ShowTV> = mutableListOf()

    fun setData(data: List<ShowTV>?) {
        this.listShowTV.clear()
        data?.let { this.listShowTV.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowTVViewHolder {
        return ShowTVViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_show_tv, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return this.listShowTV.size
    }

    override fun onBindViewHolder(holder: ShowTVViewHolder, position: Int) {
        holder.bindHistoricalAdvance(listShowTV[position])
    }

    class ShowTVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindHistoricalAdvance(showTV: ShowTV) {

            itemView.cv_element.setOnClickListener{
                itemView.context.startActivity(DetailActivity.newIntent(itemView.context, showTV.show?.id))
            }

            Glide.with(itemView.context).load(showTV.show?.image?.get("medium"))
                .into(itemView.iv_showTv)
            itemView.tv_nameShowTV.text = showTV.name

            if (!showTV.show?.network?.name.isNullOrEmpty()) {
                itemView.tv_networkName.visibility = View.VISIBLE
                itemView.tv_networkName.text = showTV.show?.network?.name
            }

            itemView.tv_airtime.text = showTV.airtime + " | " + showTV.airdate
        }

    }

}