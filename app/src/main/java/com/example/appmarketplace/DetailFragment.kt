package com.example.appmarketplace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmento = inflater.inflate(R.layout.fragment_detail, container, false)

        var Product = requireArguments().getString("Product")
        var Category = requireArguments().getString("Category")
        var Price = requireArguments().getString("Price")

        var textViewProduct: TextView =fragmento.findViewById(R.id.textViewProduct)
        var textViewCategory: TextView =fragmento.findViewById(R.id.textViewCategory)
        var textViewPrice: TextView =fragmento.findViewById(R.id.textViewPrice)

        textViewProduct.text= Product
        textViewCategory.text= Category
        textViewPrice.text= Price

        return fragmento
    }
}