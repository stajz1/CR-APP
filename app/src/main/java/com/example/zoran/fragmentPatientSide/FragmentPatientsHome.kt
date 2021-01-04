package com.example.zoran.fragmentPatientSide

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
import com.example.zoran.R
import com.example.zoran.logError
import kotlinx.android.synthetic.main.patient_home.view.*
import kotlinx.android.synthetic.main.patient_home.view.cardView1
import kotlinx.android.synthetic.main.patient_home.view.cardView2
import kotlinx.android.synthetic.main.patient_home.view.cardView3
import kotlinx.android.synthetic.main.patient_home.view.cardView5
import kotlinx.android.synthetic.main.staff_patient_info.view.*

class FragmentPatientsHome: Fragment() {

    private var root:View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.patient_home, container, false)

        val pref =
            requireContext().getSharedPreferences("loggedIn", 0)
        val editor = pref.edit()

        root?.textView4?.text = "Hallo ".plus(pref.getString("fullName" , "Paitent1"))

        val id: Int? = pref.getInt("patientId" , 0)
        requireContext().logError(id.toString())
        root?.textView35?.setOnClickListener {
            editor.putBoolean("checkSignIn", false)
            editor.apply()
            findNavController().navigate(R.id.action_fragmentPatientsHome_to_fragmentLoginSelection)
        }

        root?.cardView1?.setOnClickListener {
            val bundle = Bundle()
            id?.let { bundle.putInt("id", it) }
            bundle.putString("fullName", root?.textView4?.text.toString())
            findNavController().navigate(R.id.action_fragmentPatientsHome_to_fragmentVitalSigns, bundle)

        }

        root?.cardView5?.setOnClickListener {
            val startMillis = System.currentTimeMillis()
            val builder: Uri.Builder = CalendarContract.CONTENT_URI.buildUpon()
            builder.appendPath("time")
            ContentUris.appendId(builder, startMillis)
            val intent = Intent(Intent.ACTION_VIEW).setData(builder.build())
            startActivity(intent)
        }

        root?.cardView2?.setOnClickListener {
            val bundle = Bundle()
            id?.let { bundle.putInt("id", it) }
            bundle.putString("fullName", root?.textView4?.text.toString())
            findNavController().navigate(R.id.action_fragmentPatientsHome_to_fragmentPatientExercise, bundle)

        }

        root?.cardView3?.setOnClickListener {
            val bundle = Bundle()
            id?.let { bundle.putInt("id", it) }
            bundle.putString("fullName", root?.textView4?.text.toString())
            findNavController().navigate(R.id.action_fragmentPatientsHome_to_fragmentHistory, bundle)

        }

        return root;
    }

}