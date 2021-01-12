package com.example.zoran

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_terms_and_con.view.*

class Fragmenttermsandcon : Fragment() {

    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_terms_and_con, container, false)

        root?.imageView10?.setOnClickListener {

            requireActivity().onBackPressed()
        }

        return root;
    }

}