package com.example.appmarketplace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MyTaskListAdapter (context: AppCompatActivity,
                        val info: Bundle) : RecyclerView.Adapter<MyTaskListAdapter.MyViewHolder>() {

    class MyViewHolder (val layout: View): RecyclerView.ViewHolder(layout)

    private var context: AppCompatActivity = context

    var myTaskTitles: ArrayList<String> = info.getStringArrayList("titles") as ArrayList<String>
    var myTaskPrices: ArrayList<String> = info.getStringArrayList("prices") as ArrayList<String>
    var myTaskCategory: ArrayList<String> = info.getStringArrayList("category") as ArrayList<String>

    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.task_list_items, parent, false)

        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       var textViewProducts = holder.layout.findViewById<TextView>(R.id.textViewProduct)
        textViewProducts.text = myTaskTitles[position]

        var textViewCategory = holder.layout.findViewById<TextView>(R.id.textViewCategory)
        textViewCategory.text = myTaskCategory [position]

        holder.layout.setOnClickListener{
            Toast.makeText(holder.itemView.context, textViewProducts.text, Toast.LENGTH_LONG).show()
            val datos = Bundle()
            datos.putString("Product", textViewProducts.text as String)
            datos.putString("Category", textViewCategory.text as String)
            datos.putString("Price", myTaskPrices[position])

            context.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(R.id.fragment_container_view, DetailFragment::class.java, datos, "detail")
                ?.addToBackStack("")
                ?.commit()
        }


    }

    override fun getItemCount(): Int {

       return myTaskTitles.size
    }
}