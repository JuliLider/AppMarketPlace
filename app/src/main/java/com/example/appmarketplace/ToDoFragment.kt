package com.example.appmarketplace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class ToDoFragment : Fragment() {

    private lateinit var listRecyclerView: RecyclerView
    private lateinit var myAdapter: RecyclerView.Adapter<MyTaskListAdapter.MyViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmento = inflater.inflate(R.layout.fragment_to_do, container, false)

       /* val detail1: Button = fragmento.findViewById(R.id.btn_detail_1)
        val detail2: Button = fragmento.findViewById(R.id.btn_detail_2)
        val detail3: Button = fragmento.findViewById(R.id.btn_detail_3)

        detail1.setOnClickListener(View.OnClickListener {
            val datos = Bundle()
            datos.putString("Product", resources.getString(R.string.text_product1))
            datos.putString("Category","Deportiva")
            datos.putString("Price", "$10.000")
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(R.id.fragment_container_view, DetailFragment::class.java, datos, "detail")
                ?.addToBackStack("")
                ?.commit()
        })

        detail2.setOnClickListener(View.OnClickListener {
            val datos = Bundle()
            datos.putString("Product", resources.getString(R.string.text_product2))
            datos.putString("Category","Toda Ocasión")
            datos.putString("Price", "$350.000")
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(R.id.fragment_container_view, DetailFragment::class.java, datos, "detail")
                ?.addToBackStack("")
                ?.commit()
        })

        detail3.setOnClickListener(View.OnClickListener {
            val datos = Bundle()
            datos.putString("Product", resources.getString(R.string.text_product3))
            datos.putString("Category","Formal")
            datos.putString("Price", "$80.000")
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(R.id.fragment_container_view, DetailFragment::class.java, datos, "detail")
                ?.addToBackStack("")
                ?.commit()
        })*/
        return fragmento
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var myTaskTitles: ArrayList<String> = ArrayList()
        myTaskTitles.add(resources.getString(R.string.text_product1))
        myTaskTitles.add(resources.getString(R.string.text_product2))
        myTaskTitles.add(resources.getString(R.string.text_product3))

        var myTaskPrices: ArrayList<String> = ArrayList()
        myTaskPrices.add(resources.getString(R.string.text_price1))
        myTaskPrices.add(resources.getString(R.string.text_price2))
        myTaskPrices.add(resources.getString(R.string.text_price3))

        var myTaskCategory: ArrayList<String> = ArrayList()
        myTaskCategory.add(resources.getString(R.string.text_category1))
        myTaskCategory.add(resources.getString(R.string.text_category2))
        myTaskCategory.add(resources.getString(R.string.text_category3))

        var info: Bundle = Bundle()
        info.putStringArrayList("titles", myTaskTitles)
        info.putStringArrayList("prices", myTaskPrices)
        info.putStringArrayList("category", myTaskCategory)

        listRecyclerView = requireView().findViewById(R.id.recyclerTodolist)
        myAdapter = MyTaskListAdapter(activity as AppCompatActivity, info)

        listRecyclerView.setHasFixedSize(true)
        listRecyclerView.adapter = myAdapter
        listRecyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))

    }
}