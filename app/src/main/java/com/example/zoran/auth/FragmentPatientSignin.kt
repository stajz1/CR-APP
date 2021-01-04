package com.example.zoran.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.zoran.R
import com.example.zoran.network.NetworkResponse
import com.example.zoran.toast
import kotlinx.android.synthetic.main.patient_signin.view.*
import kotlinx.android.synthetic.main.patient_signin.view.button3
import kotlinx.android.synthetic.main.patient_signin.view.editTextTextPersonName6
import kotlinx.android.synthetic.main.patient_signin.view.editTextTextPersonName7

class FragmentPatientSignin: Fragment() {

    private var root: View? = null
    private var authViewModel:  AuthViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.patient_signin, container, false)

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        root?.button3?.setOnClickListener {

            root?.progressBar2?.visibility = View.VISIBLE
            root?.button3?.isEnabled = false
            if (root?.editTextTextPersonName6?.text.toString().trim().isNotEmpty()){
                if (root?.editTextTextPersonName7?.text.toString().trim().isNotEmpty()){
                    authViewModel?.signIn(mobileNo = root?.editTextTextPersonName6?.text.toString().trim(), password =  root?.editTextTextPersonName7?.text.toString().trim(), userType =  "USER")
                }else{
                    root?.editTextTextPersonName7?.error = "Please Fill this field"
                }
            }else{
                root?.editTextTextPersonName6?.error = "Please Fill this field"
            }
        }

        authViewModel?.response?.observe(viewLifecycleOwner, Observer {
            when(it){

                is NetworkResponse.ERROR->{
                    root?.progressBar2?.visibility = View.GONE
                    root?.button3?.isEnabled = true
                    it.error.printStackTrace()
                    requireContext().toast("Failed to do login")
                }

                is NetworkResponse.SUCCESS.Auth -> {
                    root?.progressBar2?.visibility = View.GONE
                    root?.button3?.isEnabled = true

                    val pref =
                        requireContext().getSharedPreferences("loggedIn", 0)
                    val editor = pref.edit()
                    editor.putBoolean("checkSignIn", true)
                    editor.putBoolean("staff", false)
                    editor.putInt("patientId", it.response.id)
                    editor.putString("fullName", it.response.fullName)
                    editor.apply()

                    val bundle = Bundle()
                    bundle.putInt("id", it.response.id)
                    findNavController().navigate(R.id.action_fragmentPatientSignin_to_fragmentPatientsHome, bundle)
                }

            }
        })
        return root;
    }

}