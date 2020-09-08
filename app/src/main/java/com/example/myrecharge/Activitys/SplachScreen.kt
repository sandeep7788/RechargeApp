package com.example.myrecharge.Activitys

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myrecharge.Helper.Local_data
import com.example.myrecharge.Helper.Method_collection
import com.example.myrecharge.Helper.Services
import com.example.myrecharge.Helper.Var
import com.example.myrecharge.Pojo.Model_Login
import com.example.myrecharge.R
import de.mateware.snacky.Snacky
import pl.droidsonroids.gif.GifImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SplachScreen : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec
    var f39t: TextView? = null
    var t1: TextView? = null
    var version: String? = null
    var pref= Local_data(this@SplachScreen)
    var mdevice:String="0000"
    lateinit var gifview:GifImageView
    lateinit var i_re_try:ImageView

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach_screen)
        pref.setMyappContext(this@SplachScreen)
        gifview=findViewById(R.id.gifview)
        i_re_try=findViewById(R.id.i_re_try)

//        StartAnimations()

        get_deviceId()
        network_snakbar()
        i_re_try.setOnClickListener {
            network_snakbar()
        }


        var MyReceiver: BroadcastReceiver?= null;
        MyReceiver = com.example.myrecharge.Helper.MyReceiver()
        registerReceiver(MyReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

/*
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity



            if(pref.ReadStringPreferences(Var.PREF_IsLogin).toString().trim().equals("1"))
            {
                startActivity(Intent(this, DashboardActivity::class.java))
            }
            else
            {
                startActivity(Intent(this, Login_Activity::class.java))
            }

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
*/

    }

/*
    private fun StartAnimations() {
        var anim = AnimationUtils.loadAnimation(this, R.anim.alpha)
        anim.reset()
        val l = findViewById<View>(R.id.lin_lay) as RelativeLayout
        l.clearAnimation()
        l.startAnimation(anim)
        anim = AnimationUtils.loadAnimation(this, R.anim.translate)
        anim.reset()
        val iv = findViewById<View>(R.id.img) as ImageView
        iv.clearAnimation()
        iv.startAnimation(anim)
        anim = AnimationUtils.loadAnimation(this, R.anim.a)
        anim.reset()
        this.f39t!!.clearAnimation()
        this.f39t!!.startAnimation(anim)
        anim = AnimationUtils.loadAnimation(this, R.anim.b)
        anim.reset()
        this.t1!!.clearAnimation()
        this.t1!!.startAnimation(anim)
    }
*/

    fun login()
    {
        gifview.visibility=View.VISIBLE
        var methods= Method_collection(this@SplachScreen)
        methods.show_Progress_dialog()
        var preferences= Services()
        val loginResponseCall: Call<Model_Login?>? = preferences!!.getlogin()!!.Login_m(
            "auth",
            pref.ReadStringPreferences(Var.PREF_Login_Id).toString().trim(),
            pref.ReadStringPreferences(Var.PREF_Login_password).toString().trim(),"1234")


        Log.e("@@login",pref.ReadStringPreferences(Var.PREF_Login_Id).toString().trim())
        Log.e("@@password",pref.ReadStringPreferences(Var.PREF_Login_password).toString().trim())
        loginResponseCall!!.enqueue(object : Callback<Model_Login?>
        {
            override fun onResponse(call: Call<Model_Login?>, response: Response<Model_Login?>) {
                methods.dismis_Progress_dialog()
                if(response.isSuccessful)
                {
                    Log.e("@@data",response.body()!!.status)
                    Log.e("@@data",response.body()!!.message)
                    Toast.makeText(this@SplachScreen,""+response.body()?.message.toString(),
                        Toast.LENGTH_SHORT).show()

                    if(response.body()!!.status.toString().equals("Success"))
                    {

                        pref.writeStringPreference(
                            Var.PREF_name, response.body()?.data?.get(0)?.membername+" "
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
                            Var.PREF_balance2, response.body()!!.data.get(0).balance2.toString()
                        )

                        pref.writeStringPreference(
                            Var.PREF_IsLogin,"1"
                        )
                        Log.d("@@ id", response.body()!!.data.get(0).loginid.toString()+" ")
                        startActivity(Intent(this@SplachScreen,DashboardActivity::class.java))
                        finish()
                    }
                    else
                    {
                        startActivity(Intent(this@SplachScreen, Login_Activity::class.java))
                        finish()
                    }
                }
                else
                {
                    Toast.makeText(this@SplachScreen," "+response.message().toString(), Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SplachScreen, Login_Activity::class.java))
                    finish()
                }
            }
            override fun onFailure(call: Call<Model_Login?>, t: Throwable) {
                startActivity(Intent(this@SplachScreen, Login_Activity::class.java))
                finish()
                Toast.makeText(this@SplachScreen," "+t.message.toString(), Toast.LENGTH_SHORT).show()
                methods.dismis_Progress_dialog()
            }

        })
    }

    fun get_deviceId()
    {
        mdevice = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

        pref.writeStringPreference(
            Var.PREF_device,mdevice
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun network_snakbar() {

        var MyReceiver: BroadcastReceiver? = null;
        MyReceiver = com.example.myrecharge.Helper.MyReceiver()
        registerReceiver(MyReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        if (!isOnline(this@SplachScreen)) {
            Snacky.builder()
                .setActivity(this@SplachScreen)
                .setActionText("Hide")
                .setText("Please Check Internet Connection....")
                .setDuration(Snacky.LENGTH_INDEFINITE)
                .build()
                .show()
            i_re_try.visibility=View.VISIBLE

        }
        else
        {
            login()
            i_re_try.visibility=View.GONE
        }
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

}