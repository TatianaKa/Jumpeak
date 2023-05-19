package com.example.jumpeak.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jumpeak.model.Subject


class SubjectAdapter(private val subjectList: ArrayList<Subject>, private val listener: Listener): RecyclerView.Adapter<SubjectAdapter.SubjectHolder>() {

    //сделать цвет выделенного объекта
   // var cardViewList: ArrayList<CardView> = ArrayList()

    class SubjectHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvName: TextView =itemView.findViewById(com.example.jumpeak.R.id.tvItem)
      //  var cardView: CardView = itemView.findViewById(com.example.jumpeak.R.id.cardView)
        fun bind(subject: Subject, listener: Listener){
            itemView.setOnClickListener {
                listener.onClick(subject = subject)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(com.example.jumpeak.R.layout.list_item,parent,false)
        return SubjectHolder(itemView)

    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: SubjectHolder, position: Int) {
        holder.tvName.text=subjectList[position].name
        holder.bind(subjectList[position],listener)
       /* cardViewList.add(holder.cardView);
        holder.cardView.setOnClickListener {
            for (cardView in cardViewList) {
                cardView.setCardBackgroundColor(com.example.jumpeak.R.color.white)
            }
            holder.cardView.setCardBackgroundColor(com.example.jumpeak.R.color.violet)

        }*/
    }
    interface Listener{
        fun onClick(subject: Subject){

        }
    }
}