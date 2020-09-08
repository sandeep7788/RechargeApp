package com.example.myrecharge.Fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myrecharge.Adapter.Adapter
import com.example.myrecharge.Helper.ApiInterface
import com.example.myrecharge.Helper.Method_collection
import com.example.myrecharge.Pojo.OperatorModel
import com.example.myrecharge.R
import com.example.myrecharge.databinding.FragmentPostpaidBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Postpaid_fragment : Fragment() {
    lateinit var thiscontext: Context
    lateinit var mainBinding : FragmentPostpaidBinding
    lateinit var arrayAdapter:ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { mainBinding = DataBindingUtil.inflate(
        inflater,
        R.layout.fragment_postpaid, container, false
    )
        thiscontext=container!!.context
        arrayAdapter=ArrayAdapter<String>(thiscontext, android.R.layout.select_dialog_singlechoice)
        setFram(Adapter())
        mainBinding.operator.setOnClickListener {
            operator_setup()
        }

        mainBinding.ivPhoneBook.setOnClickListener {
            browseContacts()
        }

        return mainBinding.root
    }
    private fun browseContacts() {
        val pickContactIntent =
            Intent("android.intent.action.PICK", Uri.parse("content://contacts"))
        pickContactIntent.type = "vnd.android.cursor.dir/phone_v2"
        startActivityForResult(pickContactIntent, 1)
    }

    fun setFram(fram: Fragment)
    {


        val newFragment = fram
        requireFragmentManager().beginTransaction()
            .replace(R.id.f1, newFragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()

    }

    fun dialog(context: Context)
    {
        var strName="0"
        val builderSingle: AlertDialog.Builder = AlertDialog.Builder(context,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
        builderSingle.setIcon(R.drawable.operator_status)
        builderSingle.setTitle("Select Any Operator :-")

        builderSingle.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

        builderSingle.setAdapter(arrayAdapter,
            DialogInterface.OnClickListener { dialog, which ->
                strName = arrayAdapter.getItem(which).toString()
                val builderInner: AlertDialog.Builder = AlertDialog.Builder(context)
                builderInner.setMessage(strName)
                builderInner.setTitle("Select Any Operator")
                builderInner.setPositiveButton("Ok",
                    DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
                builderInner.show()
                mainBinding.operator.setText(strName+" ")
            })
        builderSingle.show()
    }

    private fun operator_setup()
    {
        var methods= Method_collection(thiscontext)
        methods.show_Progress_dialog()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://paymyrecharge.in/APIMANAGER/")
            .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
            .build()

        var api:ApiInterface= retrofit.create(ApiInterface::class.java)
        var call:Call<List<OperatorModel>> = api.getOperatorList(1)

        call.enqueue(object: Callback<List<OperatorModel>>
        {
            override fun onResponse(
                call: Call<List<OperatorModel>>,
                response: Response<List<OperatorModel>>
            ) {
                if(response.isSuccessful)
                {
                    methods.dismis_Progress_dialog()
                    for(i in 0 until response.body()!!.size)
                    {
                        Log.e("@@response",response!!.body()!!.get(i).operatorName)
                        arrayAdapter.add(response!!.body()!!.get(i).operatorName.toString()+" ")
                    }
                    dialog(thiscontext)
                }
            }

            override fun onFailure(call: Call<List<OperatorModel>>, t: Throwable) {
                Log.e("@@error100",t.message.toString())
                methods.dismis_Progress_dialog()
                Toast.makeText(thiscontext," "+t.message.toString(), Toast.LENGTH_SHORT)
            }

        })
    }
}