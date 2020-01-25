package com.jlapps.demorx.busca

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jlapps.demorx.R

class ClubeAdapter(
    private val context: Context,
    private var clubes: MutableList<Clube>
) :
    RecyclerView.Adapter<ClubeAdapter.ClubeViewHolder>() {
    override fun onBindViewHolder(holder: ClubeViewHolder, position: Int) {
        val clube = clubes.get(position)
        holder.bindView(clube)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ClubeViewHolder(inflater.inflate(R.layout.clube_item, parent, false))
    }


    override fun getItemCount(): Int {
        return clubes.size
    }

    inner class ClubeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvClube: TextView = itemView.findViewById(R.id.tvClube) as TextView

        fun bindView(clube: Clube) {
            tvClube.text = clube.nome
        }
    }

    fun filter(lstFiltred: List<Clube>) {
        clubes = ArrayList<Clube>()
        clubes.addAll(lstFiltred)
        notifyDataSetChanged()
    }

}
