package com.example.jumpeak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jumpeak.R
import com.example.jumpeak.model.SoftSkill

class SoftSkillAdapter (private val softSkillList:ArrayList<SoftSkill>,
                        private val listener: Listener
): RecyclerView.Adapter<SoftSkillAdapter.SoftSkillHolder>() {
    class SoftSkillHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvName: TextView =itemView.findViewById(R.id.tvItem)

        fun bind(softSkill: SoftSkill, listener: Listener){
            itemView.setOnClickListener {
                listener.onClick(softSkill)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoftSkillHolder {
        val itemview= LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return SoftSkillHolder(itemview)
    }

    override fun getItemCount(): Int {
        return softSkillList.size
    }

    override fun onBindViewHolder(holder: SoftSkillHolder, position: Int) {
        holder.tvName.text=softSkillList[position].name
        holder.bind(softSkillList[position],listener)
    }
    interface Listener{
        fun onClick(softSkill: SoftSkill){
        }
    }
}