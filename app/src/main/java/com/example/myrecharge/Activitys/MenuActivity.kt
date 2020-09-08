package com.example.myrecharge.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myrecharge.Fragment.*
import com.example.myrecharge.R

class MenuActivity : AppCompatActivity() {

    var menu:String="0"
    lateinit var back:ImageView
    lateinit var t_Title:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        setContentView(R.layout.activity_menu)
        back=findViewById(R.id.back)
        t_Title=findViewById(R.id.t_Title)


        initView()

        back.setOnClickListener {
            var i= Intent(this@MenuActivity,
                DashboardActivity::class.java)
            startActivity(i)
        }





    }

    fun setFram(fram: Fragment)
    {


        val newFragment = fram
        supportFragmentManager.beginTransaction()
            .replace(R.id.fram1,newFragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        var i= Intent(this@MenuActivity,
            DashboardActivity::class.java)
        startActivity(i)
    }

    fun initView()
    {
        var i=intent
        menu=i.getStringExtra("menu")!!.trim().toString()
        Log.e("@@data", i.getStringExtra("menu").toString())
        t_Title.setText(menu.toString())

        if(menu.equals("Prepaid Recharge"))
        {
            setFram(Prepaid_fragment())

        }
        else if(menu.equals("Postpaid Recharge"))
        {
            setFram(Postpaid_fragment())
        }
        else if(menu.equals("Data Card"))
        {
            setFram(DataCard_Fragment())
        }
        else if(menu.equals("DTH Recharge"))
        {
            setFram(Dth_Fragment())
        }
        else if(menu.equals("Gas"))
        {
            setFram(Qr_codeFrgament())
        }
        else if(menu.equals("datacard"))
        {
            setFram(DataCard_Fragment())
        }
        else if(menu.equals("Landline"))
        {
            setFram(LandlineFragment())
        }
        else if(menu.equals("Electricity"))
        {
            setFram(Electricity_fragment())
        }
        else if(menu.equals("line"))
        {
            setFram(LandlineFragment())
        }
        else if(menu.equals("Fund_Request"))
        {
            setFram(Fund_RequestFragment())
        }
        else if(menu.equals("Fund_Transfer"))
        {
            setFram(Fund_TransferFragment())
        }
        else if(menu.equals("Fund Recieve"))
        {
            setFram(Fund_RecieveFragment())
        }
        ////
        else if(menu.equals("Transfer"))
        {
            setFram(Transfer_Fragment())
        }
        else if(menu.equals("Transaction"))
        {
            setFram(Transaction_Fragment())
        }


    }
}