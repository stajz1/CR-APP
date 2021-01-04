package com.example.zoran

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zoran.fragmentPatientSide.HistoryListViewModel
import com.example.zoran.network.NetworkResponse
import kotlinx.android.synthetic.main.fragment_history.view.*
import kotlinx.android.synthetic.main.fragment_history.view.imageView10
import kotlinx.android.synthetic.main.fragment_history.view.recyclerView
import kotlinx.android.synthetic.main.fragment_history.view.textView13
import kotlinx.android.synthetic.main.staff_patient_exercise.view.*

class FragmentHistory: Fragment() {

    private var root: View? = null
    private var historyList: HistoryList?= null
    private var historyListViewModel:  HistoryListViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_history, container, false)

        historyListViewModel = ViewModelProvider(this)[HistoryListViewModel::class.java]
        val patientId = arguments?.getInt("id")
        root?.textView13?.text = arguments?.getString("fullName")

        historyListViewModel?.historyList(patientId!!)

        historyList = HistoryList()

        root?.recyclerView?.layoutManager = LinearLayoutManager(context)
        root?.recyclerView?.setHasFixedSize(true)
        root?.recyclerView?.adapter = historyList

        root?.imageView10?.setOnClickListener {
            requireActivity().onBackPressed()
        }

        historyListViewModel?.response?.observe(viewLifecycleOwner, Observer {
            when(it){

                is NetworkResponse.ERROR->{
                    root?.progressBar4?.visibility = GONE
                    it.error.printStackTrace()
                    requireContext().toast("Failed to fetch list")
                }

                is NetworkResponse.SUCCESS.HistoryList -> {
                    root?.progressBar4?.visibility = GONE
                    historyList?.setData(it.response)
                }
            }
        })





        return root;
    }


}