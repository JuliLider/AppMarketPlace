package com.example.appmarketplace.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmarketplace.CarAdapter
import com.example.appmarketplace.CarEntity
import com.example.appmarketplace.R
import com.example.appmarketplace.databinding.FragmentAboutBinding
import com.google.firebase.firestore.FirebaseFirestore

class AboutFragment: Fragment(), CarAdapter.OnItemClickListener   {

    private lateinit var aboutViewModel: AboutViewModel
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!
    private lateinit var recycleView: RecyclerView
    private var db = FirebaseFirestore.getInstance()
    private var total = 0
    private var totalText : TextView?=null
    private var listCar = mutableListOf<CarEntity>()
    private lateinit var carAdapter: CarAdapter
    private var idCar = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aboutViewModel = ViewModelProvider(this).get(AboutViewModel::class.java)

        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Recycler
        recycleView = root.findViewById<RecyclerView>(R.id.reclyclerCar)
        recycleView.layoutManager = LinearLayoutManager(activity)
        recycleView.setHasFixedSize(true)

        getAllCarNew()
        carAdapter = CarAdapter(listCar,this);
        recycleView.adapter = carAdapter;

        totalText = root.findViewById<TextView>(R.id.total)

        var buttonBuy = root.findViewById<Button>(R.id.buy)
        buttonBuy.setOnClickListener {

            db.collection("car").document(idCar).update("state",true)

            val prefsDeleteCar= requireActivity().getSharedPreferences(resources.getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefsDeleteCar.remove("car")
            prefsDeleteCar.apply()

            var nav = Navigation.createNavigateOnClickListener(R.id.nav_slideshow)
            nav.onClick(view);
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getAllCarNew() {
        listCar.clear()

        if(idCar != null && idCar.isNotEmpty() && idCar != "null"){

            db.collection("car").document(idCar).collection("products")
                .get().addOnSuccessListener {
                    if (it.any()) {
                        for (item in it) {
                            listCar.add(
                                CarEntity(
                                    item.data["image"].toString(),
                                    item.data["title"].toString(),
                                    item.data["cost"].toString(),
                                    item.data["amount"].toString(),
                                    item.id.toString()
                                )
                            )

                            total += (item.data["cost"].toString()
                                .toInt() * item.data["amount"].toString().toInt())

                            totalText!!.text = total.toString()
                        }
                        carAdapter.notifyDataSetChanged();
                    }
                }

        }

    }

    override fun onItemClick(position: Int) {
        val carItem: CarEntity = listCar[position]

        if(idCar != null && idCar.isNotEmpty() && idCar != "null"){
            db.collection("car").document(idCar).collection("products").document(carItem.id).delete()
        }

        getAllCarNew();
    }


}