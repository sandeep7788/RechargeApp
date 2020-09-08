package com.example.myrecharge.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myrecharge.Adapter.Adapter
import com.example.myrecharge.R
import com.example.myrecharge.databinding.FragmentDthBinding

class Dth_Fragment : Fragment() {
    lateinit var thiscontext: Context
    lateinit var mainBinding : FragmentDthBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        mainBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_dth, container, false)
        thiscontext=container!!.context


        setFram(Adapter())

        setFram(Adapter())



        return mainBinding.root
    }
    fun setFram(fram: Fragment)
    {


        val newFragment = fram
        requireFragmentManager().beginTransaction()
            .replace(R.id.f1,newFragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()

    }
}