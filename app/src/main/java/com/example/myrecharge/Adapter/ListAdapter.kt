package com.example.myrecharge.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecharge.Helper.Var
import com.example.myrecharge.Pojo.ModelPassbook_Att
import com.example.myrecharge.Pojo.ModelTransactionFilter
import com.example.myrecharge.Pojo.ModelTransactionFilter_Att
import com.example.myrecharge.R
import com.squareup.picasso.Picasso

class ListAdapter(private val list: List<ModelPassbook_Att>, var context:Context)
    : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.amount1!!.setText(list.get(position).amount.toString())
        holder.t_date!!.setText(list.get(position).date.toString())
        holder.t_description!!.setText(list.get(position).desc.toString())

        Picasso.with(context)
            .load(list.get(position).icon)
            .into(holder.i_image)
    }

    override fun getItemCount(): Int = list.size

}
class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_adapter, parent, false)) {
    var i_image: ImageView? = null
    var t_date: TextView? = null
    var amount1: TextView? = null
    var t_description: TextView? = null

    init {
        i_image = itemView.findViewById(R.id.i_image)
        t_date = itemView.findViewById(R.id.t_date)
        amount1 = itemView.findViewById(R.id.amount1)
        t_description= itemView.findViewById(R.id.t_description)

    }


}