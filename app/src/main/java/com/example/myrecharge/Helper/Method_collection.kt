package com.example.myrecharge.Helper

import android.R
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myrecharge.Pojo.Model_Login
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.github.javiersantos.materialstyleddialogs.enums.Style
import com.kaopiz.kprogresshud.KProgressHUD
import com.sandeep.AndroidDialog.AndroidDialog
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class Method_collection : Application
{
    var context:Context ?=null
    lateinit var dialog: AlertDialog

    constructor(context: Context) {
        this.context=context
        dialog= SpotsDialog.Builder().setContext(context).build()
    }
    fun getinstance(context: Context)
    {
        this.context=context
    }

    var TAG="@@method"
    var pref= Local_data(context)

   fun show_dialog(title: String):Boolean
   {
       val dialog = AndroidDialog(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
       dialog.setTitle(title)
       dialog.setColor(Color.parseColor("#041e37"))
       dialog.setPositiveListener(
           "OK"
       ) { dialog ->

           dialog.dismiss()
       }.show()
       return true
   }

    fun show_Progress_dialog()
    {
        dialog.setMessage("Please wait....")
        dialog.setCancelable(false)
        dialog.show()
    }

    fun dismis_Progress_dialog()
    {

        dialog.dismiss()
    }

    fun login():String
    {
        var status:Boolean=false
        var data="0"
        var retrofit: Retrofit? = null

        var preferences= Services()
        val loginResponseCall: Call<Model_Login?>? = preferences!!.getlogin()!!.Login_m(
            "BTX861290",
            "12345678","",""
        )
        loginResponseCall!!.enqueue(object : Callback<Model_Login?>
        {
            override fun onResponse(call: Call<Model_Login?>, response: Response<Model_Login?>) {

            }

            override fun onFailure(call: Call<Model_Login?>, t: Throwable) {

            }

        })

        return data
    }

    @SuppressLint("WrongConstant")
    fun setFram(mlayout: Int, fram: Fragment, fManager: FragmentManager)
    {

        // initial transaction should be wrapped like this
        val newFragment = fram

        fManager.beginTransaction()
            .replace(mlayout, newFragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
    fun show_AlertDialog(context: Context,title: String,desc:String)
    {
        val dialog = MaterialStyledDialog.Builder(context)
            .setTitle(title)
            .setDescription(desc)
            .build()
        dialog.show()
    }

    fun dialog(context: Context):String
    {
        var strName="0"
        val builderSingle: AlertDialog.Builder = AlertDialog.Builder(context)
        builderSingle.setIcon(android.R.drawable.sym_def_app_icon)
        builderSingle.setTitle("Select One Name:-")

        val arrayAdapter =
            ArrayAdapter<String>(context, R.layout.select_dialog_singlechoice)
        arrayAdapter.add("Hardik")
        arrayAdapter.add("Archit")
        arrayAdapter.add("Jignesh")
        arrayAdapter.add("Umang")
        arrayAdapter.add("Gatti")

        builderSingle.setNegativeButton("cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

        builderSingle.setAdapter(arrayAdapter,
            DialogInterface.OnClickListener { dialog, which ->
                strName = arrayAdapter.getItem(which).toString()
                val builderInner: AlertDialog.Builder = AlertDialog.Builder(context)
                builderInner.setMessage(strName)
                builderInner.setTitle("Your Selected Item is")
                builderInner.setPositiveButton("Ok",
                    DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
                builderInner.show()
            })
        builderSingle.show()
        return strName
    }
}