package com.example.zoran

import android.content.ContentUris
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.staff_home.view.*


class FragmentStaffHome: Fragment() {

    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.staff_home, container, false)
        val pref =
            requireContext().getSharedPreferences("loggedIn", 0)


        root?.textView4?.text = "Hallo ".plus(pref.getString("fullName", ""))

        val editor = pref.edit()

        root?.setting?.setOnClickListener {
            val startMillis = System.currentTimeMillis()
            val builder: Uri.Builder = CalendarContract.CONTENT_URI.buildUpon()
            builder.appendPath("time")
            ContentUris.appendId(builder, startMillis)
            val intent = Intent(Intent.ACTION_VIEW).setData(builder.build())
            startActivity(intent)
        }

        root?.textView36?.setOnClickListener {
            editor.putBoolean("checkSignIn", false)
            editor.apply()
            findNavController().navigate(R.id.action_fragmentStaffHome_to_fragmentLoginSelection)
        }

        root?.cardView2?.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentStaffHome_to_fragmentPatients)

        }

        root?.cardView5?.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentStaffHome_to_fragmentExerciseCategory)

        }

        return root;
    }

}