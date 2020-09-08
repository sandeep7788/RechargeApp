package com.example.myrecharge.Activitys

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myrecharge.Helper.*
import com.example.myrecharge.Pojo.ModelWallattransfer
import com.example.myrecharge.R
import com.example.myrecharge.databinding.ActivityPay2Binding
import retrofit2.Call
import retrofit2.Response

class Pay_Activity2 : AppCompatActivity() {
    lateinit var mainBinding: ActivityPay2Binding
    var mid:String="0"
    var mname:String="0"
    var pref= Local_data(this@Pay_Activity2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_pay2)
        pref.setMyappContext(this@Pay_Activity2)
        setdata()
        mainBinding.btnBack.setOnClickListener { finish() }
        mainBinding.btnPay.setOnClickListener {

            if(mainBinding.edtAmount.text.toString().isEmpty())
            {
                mainBinding.edtAmount.setError("Enter value")
            }
            else if(mainBinding.edtPassword.text.toString().isEmpty())
            {
                mainBinding.edtPassword.visibility= View.GONE
            }
            else
            {
                api_for_pay()
            }

        }

    }

    fun setdata()
    {
        mid=intent.getStringExtra("id").toString()
        mname=intent.getStringExtra("name").toString()

        mainBinding.tName.setText(mname)
        mainBinding.tdescription.setText("Rippo Wallet Linked to "+mid)
        mainBinding.tBalance.setText(pref.ReadStringPreferences(Var.PREF_balance2))

    }

    fun api_for_pay()
    {
        //https://therippco.com/api/wallet.ashx?f=transferepin&
        // fromloginid=5002081557&tologinid=5002049412&amount=10&pass=babydoll&ip=

        var pref= Local_data(this@Pay_Activity2)
        var methods= Method_collection(this@Pay_Activity2)
        methods.show_Progress_dialog()

        pref.setMyappContext(this@Pay_Activity2)

        val apiInterface: ApiInterface =
            RetrofitManager.instance!!.create(ApiInterface::class.java)
        val call: Call<ModelWallattransfer?>? =apiInterface.pay_amount(
            "transferepin",
            pref.ReadStringPreferences(Var.PREF_Login_Id),
            mid,
            mainBinding.edtAmount.text.toString(),
            mainBinding.edtPassword.text.toString().trim(),
        pref.ReadStringPreferences(Var.PREF_device))

        call!!.enqueue(object : retrofit2.Callback<ModelWallattransfer?>
        {
            override fun onFailure(call: Call<ModelWallattransfer?>, t: Throwable) {
                Toast.makeText(this@Pay_Activity2," "+t.message.toString(),Toast.LENGTH_LONG).show()
                methods.show_AlertDialog(this@Pay_Activity2,"Payment Fail.",t.message.toString().trim()+" ")
                methods.dismis_Progress_dialog()
                cln()
            }

            override fun onResponse(
                call: Call<ModelWallattransfer?>,
                response: Response<ModelWallattransfer?>
            ) {
                methods.dismis_Progress_dialog()
                cln()
                if(response.isSuccessful)
                {
                    Toast.makeText(this@Pay_Activity2," "+response.body()!!.message.toString(),Toast.LENGTH_LONG).show()
                    if(response.body()!!.status.toString().toLowerCase().trim().equals("success"))
                    {

                        Log.e("@@",response!!.body()!!.data.get(0).balance.toString()+" ")

                        pref.writeStringPreference(Var.PREF_balance2,response!!.body()!!.data.get(0).balance.toString().trim())
                    methods.show_AlertDialog(this@Pay_Activity2,"Payment Success",response!!.body()!!.message.toString().trim()+" ")

                    }

                }
            }

        })

    }

    fun cln()
    {
        mainBinding.edtPassword.setText("")
        mainBinding.edtAmount.setText("")
    }
}