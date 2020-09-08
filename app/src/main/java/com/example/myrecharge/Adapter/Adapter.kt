package com.example.myrecharge.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecharge.R
import com.example.myrecharge.databinding.FragmentAdapterBinding


class Adapter : Fragment() {
    lateinit var thiscontext: Context
    lateinit var mainBinding : FragmentAdapterBinding
    var data:ArrayList<String> ?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment

        mainBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_adapter, container, false)
        thiscontext=container!!.context
        data= ArrayList()

        data!!.add("01423")
        data!!.add("012345")
        data!!.add("797687")
        data!!.add("6211")
        data!!.add("01423")
        data!!.add("012345")
        data!!.add("797687")
        data!!.add("6211")
        data!!.add("01423")
        data!!.add("012345")
        data!!.add("797687")
        data!!.add("6211")



//        layoutManager = LinearLayoutManager(this@Adapter)
//        mainBinding.Recyclerview.layoutManager = layoutManager


       /* mainBinding.Recyclerview.layoutManager = LinearLayoutManager(thiscontext, LinearLayout.VERTICAL, false)
        mainBinding.Recyclerview.adapter =
            ListAdapter(data!!, thiscontext)
*/
        return mainBinding.root
    }
}