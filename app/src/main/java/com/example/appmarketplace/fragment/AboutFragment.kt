package com.example.appmarketplace.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmarketplace.R
import com.example.appmarketplace.databinding.FragmentAboutBinding
import com.google.firebase.firestore.FirebaseFirestore

class AboutFragment: Fragment()  {

    private lateinit var aboutViewModel: AboutViewModel
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!
    private lateinit var recycleView: RecyclerView
    private var db = FirebaseFirestore.getInstance()
    private var total = 0
    private var totalText : TextView?=null

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

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}