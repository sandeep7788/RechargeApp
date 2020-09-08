package com.example.myrecharge.Fragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.myrecharge.Activitys.MenuActivity
import com.example.myrecharge.Activitys.Pay_Activity
import com.example.myrecharge.Helper.Local_data
import com.example.myrecharge.Helper.Var
import com.example.myrecharge.R
import com.example.myrecharge.databinding.FragmentOneBinding
import com.sandeep.AndroidDialog.AndroidDialog

class Home_Fragment: Fragment() {
    lateinit var thiscontext: Context
    lateinit var mainBinding : FragmentOneBinding
    lateinit var transaction: FragmentTransaction
    var pref= Local_data(context)
    override fun onCreateView(inflater:LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View?
    {
        pref.setMyappContext(context)
        mainBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_one, container, false
        )
        thiscontext=container!!.context

        mainBinding.linearLayout.setOnClickListener{
                     go("Prepaid Recharge")
         }
        mainBinding.linearLayout1.setOnClickListener{
            go("Postpaid Recharge")

        }
        mainBinding.linearLayout2.setOnClickListener{
            go("DTH Recharge")

        }

        mainBinding.linearLayout3.setOnClickListener{
            go("Data Card")

        }
        mainBinding.linearLayout4.setOnClickListener{
//            go("Gas")
            try_later("Coming Soon!")
        }
        mainBinding.linearLayout5.setOnClickListener{
//            go("Water_Bill")
            try_later("Coming Soon!")
        }
        mainBinding.linearLayout6.setOnClickListener{
            try_later("Coming Soon!")
//            go("Electricity")
        }

        mainBinding.linearLayout7.setOnClickListener{
//            go("Landline")
            try_later("Coming Soon!")
        }
        mainBinding.linearLayout8.setOnClickListener{
//            go("Fund Recieve")
            try_later("Coming Soon!")
        }
        mainBinding.linearLayout9.setOnClickListener{
            try_later("Coming Soon!")
        }
        mainBinding.linearLayout10.setOnClickListener{
            try_later("Coming Soon!")
        }
        mainBinding.linearLayout11.setOnClickListener{
            try_later("Coming Soon!")
        }
        mainBinding.rpWallet.setOnClickListener{
            try_later(" Currently payment gateway is under maintenance \n for indian members. You will be able to use \n this service very soon !")
//            go("Fund_Request")
        }

        mainBinding.btnRpc.setOnClickListener {
            try_later("Coming Soon!")
        }

        mainBinding.Transfer.setOnClickListener{
            go("Transfer")
        }

        mainBinding.Transaction.setOnClickListener{
            go("Transaction")
        }

        mainBinding.Amount.setOnClickListener{
            dialog()
        }
        mainBinding.l05.setOnClickListener{
          setFram(RechargeFragment())
        }

        mainBinding.lAddfund.setOnClickListener {
            setFram1(Add_fundFragment())
        }


        mainBinding.l06.setOnClickListener{
            val dialog = AndroidDialog(thiscontext)
            dialog.setTitle("No Activity")
            dialog.contentText = "Coming Soon"
            dialog.setPositiveListener(
                "Ok"
            ) { dialog ->
                Toast.makeText(
                    thiscontext,
                    dialog.positiveText.toString(),
                    Toast.LENGTH_SHORT

                ).show()
                dialog.dismiss()

            }.show()
        }

        mainBinding.l07.setOnClickListener{
        }
        mainBinding.l08.setOnClickListener{

        }

        mainBinding.lPassbook.setOnClickListener {
            setFram1(PassbookFragment())
        }
        mainBinding.btnPay.setOnClickListener {
            startActivity(Intent(thiscontext,Pay_Activity::class.java))
        }

        return mainBinding.root
    }

    fun setFram(fram: Fragment)
    {


        val newFragment = fram
        requireFragmentManager().beginTransaction()
            .replace(R.id.swipe, newFragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()

    }
    fun setFram1(fram: Fragment)
    {
        val newFragment = fram
        requireFragmentManager().beginTransaction()
            .replace(R.id.frame, newFragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    fun go(name: String)
    {
        var i=Intent(thiscontext, MenuActivity::class.java)
        i.putExtra("menu", name)
        startActivity(i)
    }

    fun dialog()
    {
        val dialog = AndroidDialog(thiscontext)
        dialog.setColor(Color.parseColor("#041e37"))
        dialog.setTitle("Your Current Balance")
        dialog.setColor(R.color.colorPrimaryDark)
        dialog.setContentTextColor(R.color.colorPrimaryDark)
        dialog.contentText = pref.ReadStringPreferences(Var.PREF_balance)+" "
        dialog.setPositiveListener(
            "Ok"
        ) { dialog ->
            Toast.makeText(
                thiscontext,
                dialog.positiveText.toString(),
                Toast.LENGTH_SHORT
            ).show()
            dialog.dismiss()
        }.show()

    }

    fun try_later(titile:String)
    {
        val dialog = AndroidDialog(thiscontext, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
        dialog.setColor(Color.parseColor("#041e37"))
        dialog.setTitle(titile)
        dialog.setColor(R.color.colorPrimaryDark)
        dialog.setContentTextColor(R.color.colorPrimaryDark)
        dialog.setPositiveListener(
            "Ok"
        ) { dialog ->
            Toast.makeText(
                thiscontext,
                dialog.positiveText.toString(),
                Toast.LENGTH_SHORT
            ).show()
            dialog.dismiss()
        }.show()
    }
}