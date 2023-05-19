package com.example.jumpeak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jumpeak.R
import com.example.jumpeak.model.HardSkill

class HardSkillAdapter (private val hardSkillList:ArrayList<HardSkill>,
                        private val listener: Listener
): RecyclerView.Adapter<HardSkillAdapter.HardSkillHolder>() {
    class HardSkillHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvName: TextView =itemView.findViewById(R.id.tvItem)

        fun bind(hardSkill: HardSkill, listener: Listener){
            itemView.setOnClickListener {
                listener.onClick(hardSkill)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HardSkillHolder {
        val itemview= LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return HardSkillHolder(itemview)
    }

    override fun getItemCount(): Int {
        return hardSkillList.size
    }

    override fun onBindViewHolder(holder: HardSkillHolder, position: Int) {
        holder.tvName.text=hardSkillList[position].name
        holder.bind(hardSkillList[position],listener)
    }
    interface Listener{
        fun onClick(hardSkill: HardSkill){
        }
    }
}