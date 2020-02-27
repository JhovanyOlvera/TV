package com.claro.tv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.claro.tv.R
import com.claro.tv.model.pojo.Show
import com.claro.tv.ui.DetailActivity
import kotlinx.android.synthetic.main.item_show_tv.view.*
import java.lang.StringBuilder

class ShowAdapter : RecyclerView.Adapter<ShowAdapter.ShowViewHolder>() {
    private val listShow: MutableList<Show> = mutableListOf()

    fun setData(data: List<Show>?) {
        this.listShow.clear()
        data?.let { this.listShow.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        return ShowViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_show_tv, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return this.listShow.size
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bindHistoricalAdvance(listShow[position])
    }

    class ShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindHistoricalAdvance(show: Show) {
            itemView.cv_element.setOnClickListener{
                itemView.context.startActivity(DetailActivity.newIntent(itemView.context, show.id))
            }
            Glide.with(itemView.context).load(show?.image?.get("medium"))
                .placeholder(R.drawable.blank_product)
                .into(itemView.iv_showTv)


            itemView.tv_nameShowTV.text = show.name

            if (!show?.network?.name.isNullOrEmpty()) {
                itemView.tv_networkName.visibility = View.VISIBLE
                itemView.tv_networkName.text = show?.network?.name
            }

            var time = show.schedule?.time

            var days = StringBuilder()
            for (a in show.schedule?.days!!) {
                days.append("$a ")
            }

            if (!time.isNullOrEmpty() && !days.isNullOrEmpty()) {
                itemView.tv_airtime.text = "$time | $days"
            } else if (time.isNullOrEmpty() && !days.isNullOrEmpty()) {
                itemView.tv_airtime.text = "$days"
            } else if (!time.isNullOrEmpty() && days.isNullOrEmpty()) {
                itemView.tv_airtime.text = "$time"
            }

        }

    }

}