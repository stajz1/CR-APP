package com.example.zoran

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class FragmentSplashScreen: Fragment() {

    private var root: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_splace_screen, container, false)

        val pref =
            requireContext().getSharedPreferences("loggedIn", 0)


        Handler().postDelayed(Runnable {
            if (pref.getBoolean("checkSignIn" , false)){

                if (pref.getBoolean("staff" , false)){
                    findNavController().navigate(R.id.action_fragmentSplashScreen_to_fragmentStaffHome)
                }else{
                    findNavController().navigate(R.id.action_fragmentSplashScreen_to_fragmentPatientsHome)
                }

            }else{
                findNavController().navigate(R.id.action_fragmentSplashScreen_to_fragmentLoginSelection)
            }


        }, 3000)

        return root;
    }


}