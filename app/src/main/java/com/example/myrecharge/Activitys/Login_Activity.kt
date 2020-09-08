package com.example.myrecharge.Activitys

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import com.example.myrecharge.Helper.Local_data
import com.example.myrecharge.Helper.Services
import com.example.myrecharge.Helper.Var
import com.example.myrecharge.Pojo.Model_Login
import com.example.myrecharge.R
import com.example.myrecharge.databinding.ActivityLoginBinding
import de.mateware.snacky.Snacky
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login_Activity: AppCompatActivity() {

    lateinit var mainBinding :ActivityLoginBinding
    lateinit var transaction: FragmentTransaction
    var pref= Local_data(this@Login_Activity)
    var TAG="@@login"
    lateinit var dialog: AlertDialog
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        mainBinding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_login
            )
        network_snakbar()
        pref.setMyappContext(this@Login_Activity)
        dialog= SpotsDialog.Builder().setContext(this@Login_Activity).build()
        dialog.setCancelable(false)

        mainBinding.btnSignIn.setOnClickListener {
            if(mainBinding.edtLoginID.text.isEmpty())
            {
                mainBinding.edtLoginID.setError("Please Enter Login-ID")
            }
            else if(mainBinding.edtPassword.text.isEmpty())
            {
                mainBinding.edtPassword.setError("Please Enter Password")
            }
            else
            {
                login()
            }
        }
    }

    fun login()
    {
        dialog.show()
        var preferences= Services()
        val loginResponseCall: Call<Model_Login?>? = preferences!!.getlogin()!!.Login_m(
            "auth",
            mainBinding.edtLoginID.text.toString(),mainBinding.edtPassword.text.toString(),"1234")

        loginResponseCall!!.enqueue(object : Callback<Model_Login?>
        {
            override fun onResponse(call: Call<Model_Login?>, response: Response<Model_Login?>) {
                dialog.dismiss()
            if(response.isSuccessful)
            {
                Log.e("@@data",response.body()!!.status)
                Log.e("@@data",response.body()!!.message)
                Toast.makeText(this@Login_Activity,""+response.body()?.message.toString(),Toast.LENGTH_SHORT).show()

                if(response.body()!!.status.toString().equals("Success"))
                {

                    pref.writeStringPreference(
                        Var.PREF_name, response.body()?.data?.get(0)?.membername+" "
                    )
                    pref.writeStringPreference(
                        Var.PREF_Login_password, mainBinding.edtPassword.text.toString()
                    )
                    pref.writeStringPreference(
                        Var.PREF_membername, response.body()!!.data.get(0).membername+" "
                    )
                    pref.writeStringPreference(
                        Var.PREF_email, response.body()!!.data.get(0).email+" "
                    )
                    pref.writeStringPreference(
                        Var.PREF_mobile, response.body()!!.data.get(0).mobile+" "
                    )
                    pref.writeStringPreference(
                        Var.PREF_balance, response.body()!!.data.get(0).balance.toString()
                    )

                    pref.writeStringPreference(
                        Var.PREF_address, response.body()!!.data.get(0).address+" "
                    )
                    pref.writeStringPreference(
                        Var.PREF_city, response.body()!!.data.get(0).city+" "
                    )

                    pref.writeStringPreference(
                        Var.PREF_state, response.body()!!.data.get(0).state+" "
                    )
                    pref.writeStringPreference(
                        Var.PREF_balance2, response.body()!!.data.get(0).balance2.toString()
                    )
                    pref.writeStringPreference(
                        Var.PREF_totaldownline, response.body()!!.data.get(0).totaldownline.toString()
                    )

                    pref.writeStringPreference(
                        Var.PREF_totaldirect, response.body()!!.data.get(0).totaldirect.toString()
                    )
                    pref.writeStringPreference(
                        Var.PREF_Login_Id, response.body()!!.data.get(0).loginid.toString()
                    )
                    pref.writeStringPreference(
                        Var.PREF_user_image, response.body()!!.data.get(0).rankimage.toString()
                    )
                    pref.writeStringPreference(
                        Var.PREF_IsLogin,"1"
                    )
                    Log.d(TAG + " id", response.body()!!.data.get(0).loginid.toString()+" ")
                    startActivity(Intent(this@Login_Activity,DashboardActivity::class.java))
                }
            }
                else
            {
                Toast.makeText(this@Login_Activity," "+response.message().toString(),Toast.LENGTH_SHORT).show()
            }
            }
            override fun onFailure(call: Call<Model_Login?>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(this@Login_Activity," "+t.message.toString(),Toast.LENGTH_SHORT).show()
            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("@@Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("@Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("@@Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun network_snakbar() {

        var MyReceiver: BroadcastReceiver? = null;
        MyReceiver = com.example.myrecharge.Helper.MyReceiver()
        registerReceiver(MyReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        if (!isOnline(this@Login_Activity)) {
            Snacky.builder()
                .setActivity(this@Login_Activity)
                .setActionText("Hide")
                .setText("Please Check Internet Connection....")
                .setDuration(Snacky.LENGTH_INDEFINITE)
                .build()
                .show()

        }
    }
}