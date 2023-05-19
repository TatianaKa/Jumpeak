package com.example.jumpeak.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jumpeak.model.Sub
import com.example.jumpeak.model.Subject

class SubAdapter(private val subList: ArrayList<Sub>,
                 private val listener: Listener):
    RecyclerView.Adapter<SubAdapter.SubHolder>() {

    //сделать цвет выделенного объекта
    // var cardViewList: ArrayList<CardView> = ArrayList()

    class SubHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvName: TextView =itemView.findViewById(com.example.jumpeak.R.id.tvItem)
        //  var cardView: CardView = itemView.findViewById(com.example.jumpeak.R.id.cardView)
        fun bind(sub: Sub, listener: Listener){
            itemView.setOnClickListener {
                listener.onClick(sub)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(com.example.jumpeak.R.layout.list_item,parent,false)
        return SubHolder(itemView)

    }

    override fun getItemCount(): Int {
        return subList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: SubHolder, position: Int) {
        holder.tvName.text=subList[position].name
        holder.bind(subList[position],listener)
        /* cardViewList.add(holder.cardView);
         holder.cardView.setOnClickListener {
             for (cardView in cardViewList) {
                 cardView.setCardBackgroundColor(com.example.jumpeak.R.color.white)
             }
             holder.cardView.setCardBackgroundColor(com.example.jumpeak.R.color.violet)

         }*/
    }
    interface Listener{
        fun onClick(sub: Sub){

        }
    }
}