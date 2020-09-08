package com.example.myrecharge.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myrecharge.Helper.Local_data
import com.example.myrecharge.Helper.Var
import com.example.myrecharge.R
import com.example.myrecharge.databinding.FragmentProfileBinding
import com.squareup.picasso.Picasso

class Profile_Fragment : Fragment() {
    lateinit var thiscontext: Context
    lateinit var mainBinding : FragmentProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {

        mainBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_profile, container, false)
        thiscontext=container!!.context

        var pref= Local_data(context)
        mainBinding.tvName.setText(pref.ReadStringPreferences(Var.membername))
        mainBinding.tvAddress.setText(pref.ReadStringPreferences(Var.PREF_address))
        mainBinding.tvMobile.setText(pref.ReadStringPreferences(Var.PREF_mobile))
        mainBinding.edtBalance.setText(pref.ReadStringPreferences(Var.PREF_balance))
        mainBinding.loginId.setText(pref.ReadStringPreferences(Var.PREF_Login_Id))

        Picasso.with(thiscontext)
            .load(pref.ReadStringPreferences(Var.PREF_user_image))
            .into(mainBinding.pImage)

        return mainBinding.root
    }
}