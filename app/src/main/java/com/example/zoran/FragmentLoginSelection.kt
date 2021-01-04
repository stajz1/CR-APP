package com.example.zoran

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.view.*

class FragmentLoginSelection: Fragment() {

    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)

        root?.button?.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentLoginSelection_to_fragmentPatientSignin)
        }

        root?.button2?.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentLoginSelection_to_fragmentStaffSignIn)
        }

        return root;
    }

}