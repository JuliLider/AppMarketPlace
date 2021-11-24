package com.example.appmarketplace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ToDoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmento = inflater.inflate(R.layout.fragment_to_do, container, false)

        /*val detail1: Button = fragmento.findViewById(R.id.btn_detail_1)
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
}