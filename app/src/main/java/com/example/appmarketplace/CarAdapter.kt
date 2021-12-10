package com.example.appmarketplace
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.graphics.drawable.Drawable
import android.widget.Button
import android.widget.Toast
import com.squareup.picasso.Picasso
import java.io.InputStream
import java.net.URL


public  class CarAdapter(private val dataSet: MutableList<CarEntity>,private val listener: CarAdapter.OnItemClickListener) :
    RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var carEntity = dataSet[position]

        holder.titleItems.text = carEntity.title;

        var total = carEntity.cost.toInt() * carEntity.amount.toInt()
        holder.costItems.text = carEntity.cost +" x "+ carEntity.amount + " = " + total

        Picasso.get().load(carEntity.imagen).into(holder.imagenItem);

    }

    override fun getItemCount():Int{
        return dataSet.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val titleItems :TextView = itemView.findViewById<TextView>(R.id.titleItem)
        val costItems :TextView = itemView.findViewById<TextView>(R.id.costItem)
        val imagenItem :ImageView = itemView.findViewById<ImageView>(R.id.imagenItem)
        val delete :Button = itemView.findViewById<Button>(R.id.delete)

        init {
            delete.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            if(adapterPosition != RecyclerView.NO_POSITION){
                listener.onItemClick(adapterPosition)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }



}