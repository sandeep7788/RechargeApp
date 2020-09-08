package com.example.myrecharge.Fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myrecharge.Adapter.Adapter
import com.example.myrecharge.R
import com.example.myrecharge.databinding.FragmentDataCardBinding


class DataCard_Fragment: Fragment() {
    lateinit var thiscontext: Context
    lateinit var mainBinding : FragmentDataCardBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        mainBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_data_card, container, false)
        thiscontext=container!!.context


        setFram(Adapter())


        mainBinding.ivPhoneBook.setOnClickListener {
            browseContacts()
        }


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
    private fun browseContacts() {
        val pickContactIntent =
            Intent("android.intent.action.PICK", Uri.parse("content://contacts"))
        pickContactIntent.type = "vnd.android.cursor.dir/phone_v2"
        startActivityForResult(pickContactIntent, 1)
    }

}