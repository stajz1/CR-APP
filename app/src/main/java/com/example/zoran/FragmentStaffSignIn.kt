package com.example.zoran

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.zoran.auth.AuthViewModel
import com.example.zoran.network.NetworkResponse
import kotlinx.android.synthetic.main.patient_signin.view.*
import kotlinx.android.synthetic.main.staff_signin.view.*
import kotlinx.android.synthetic.main.staff_signin.view.button3
import kotlinx.android.synthetic.main.staff_signin.view.editTextTextPersonName6
import kotlinx.android.synthetic.main.staff_signin.view.editTextTextPersonName7
import java.util.*

class FragmentStaffSignIn: Fragment() {

    private var root: View? = null
    private var authViewModel:  AuthViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.staff_signin, container, false)

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        root?.button3?.setOnClickListener {
            val imm =
                (Objects.requireNonNull(requireContext())
                    .getSystemService(
                        Activity.INPUT_METHOD_SERVICE
                    ) as InputMethodManager)
            imm.hideSoftInputFromWindow(root?.windowToken, 0)

            root?.progressBar?.visibility = VISIBLE
            root?.button3?.isEnabled = false
            if (root?.editTextTextPersonName6?.text.toString().trim().isNotEmpty()){
                if (root?.editTextTextPersonName7?.text.toString().trim().isNotEmpty()){
                    authViewModel?.signIn(mobileNo = root?.editTextTextPersonName6?.text.toString().trim(), password =  root?.editTextTextPersonName7?.text.toString().trim(), userType =  "STAFF")
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
                    it.error.printStackTrace()
                    root?.progressBar?.visibility = GONE
                    root?.button3?.isEnabled = true
                    requireContext().toast("Failed to do login")
                }

                is NetworkResponse.SUCCESS.Auth -> {
                    root?.progressBar?.visibility = GONE
                    root?.button3?.isEnabled = true
                    val pref =
                        requireContext().getSharedPreferences("loggedIn", 0)
                    val editor = pref.edit()
                    editor.putString("fullName", it.res.fullName)
                    editor.putBoolean("checkSignIn", true)
                    editor.putBoolean("staff", true)
                    editor.apply()
                    findNavController().navigate(R.id.action_fragmentStaffSignIn_to_fragmentStaffHome)
                }

            }
        })

        return root;
    }

}