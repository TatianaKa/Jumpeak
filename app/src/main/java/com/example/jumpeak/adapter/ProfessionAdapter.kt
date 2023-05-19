package com.example.jumpeak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jumpeak.R
import com.example.jumpeak.model.Profession

class ProfessionAdapter (private val professionList:ArrayList<Profession>,
                         private val listener: Listener):
    RecyclerView.Adapter<ProfessionAdapter.ProfessionHolder>() {
    class ProfessionHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvName: TextView =itemView.findViewById(R.id.tvItem)

        fun bind(profession: Profession, listener: Listener){
            itemView.setOnClickListener {
                listener.onClick(profession = profession)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessionHolder {
        val itemview= LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ProfessionHolder(itemview)
    }

    override fun getItemCount(): Int {
        return professionList.size
    }

    override fun onBindViewHolder(holder: ProfessionHolder, position: Int) {
        holder.tvName.text=professionList[position].name
        holder.bind(professionList[position],listener)
    }
    interface Listener{
        fun onClick(profession: Profession){
        }
    }
}