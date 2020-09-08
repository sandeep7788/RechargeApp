package com.example.myrecharge.Activitys

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.myrecharge.Fragment.*
import com.example.myrecharge.Helper.Local_data
import com.example.myrecharge.Helper.Var
import com.example.myrecharge.R
import com.example.myrecharge.databinding.ActivityDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.squareup.picasso.Picasso

class DashboardActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityDashboardBinding
    lateinit var transaction:FragmentTransaction
    var pref= Local_data(this@DashboardActivity)
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        pref.setMyappContext(this@DashboardActivity)
        mainBinding =
            DataBindingUtil.setContentView(this,R.layout.activity_dashboard)

        var MyReceiver: BroadcastReceiver?= null;
        MyReceiver = com.example.myrecharge.Helper.MyReceiver()
        registerReceiver(MyReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

//        setFram(Home_Fragment())

        val newFragment = Home_Fragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, newFragment, "home")
            .addToBackStack(null)
            .commitAllowingStateLoss()
        bottomNavigationbar()
//        setNavigationbar()
//        set_details()
        setnavigation_item()
        genrate_qr_code()

        mainBinding.txtNoteimage1.setOnClickListener {
            mainBinding.drawerLayout.openDrawer(Gravity.LEFT)
        }

        mainBinding.iQrcode.setOnClickListener {
            startActivity(Intent(this@DashboardActivity,Qr_Activity::class.java))
        }
    }

    @SuppressLint("WrongConstant")
    fun bottomNavigationbar()
    {
// mainBinding.bottomNavigation.setItemIconTintList(R.color.Gold_color)
        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
//                    setFram(Home_Fragment())
                    mainBinding.drawerLayout.closeDrawer(Gravity.START, true)
                    val newFragment = Home_Fragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, newFragment, "home")
                        .addToBackStack(null)
                        .commitAllowingStateLoss()
//                    mainBinding.navigation.menu.findItem(android.R.id.navigation_home).isChecked = true

                    mainBinding.navigation.menu.findItem(R.id.navigation_home).isChecked = true
                    true
                }
                R.id.navigation_Wallet -> {
                    mainBinding.navigation.menu.findItem(R.id.navigation_Wallet).isChecked = true
                    setFram(WalletFragment())
// handle cli

                    true
                }
                R.id.navigation_profile -> {
                    mainBinding.navigation.menu.findItem(R.id.navigation_profile).isChecked = true
                    Log.e("@@naviagtionbar_log", "reward")
                    setFram(Profile_Fragment())
// handle clil
                    true
                }
                R.id.setting -> {
                    mainBinding.navigation.menu.findItem(R.id.setting).isChecked = true
                    Log.e("@@naviagtionbar_log", "reward")
                    setFram(Setting_fragment())
// handle clil
                    true
                }

            }
            false
        }
        mainBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

/*
    fun setNavigationbar() {
        mainBinding.navigationView.setNavigationItemSelectedListener {
            when (it.getItemId()) {
                R.id.mhome -> {
                    setFram(Home_Fragment())
                    true
                }
               */
/* R.id.mrc_history -> {
                    setFram(HistoryFragment())
                     true
                }
                R.id.mincome -> {
                    setFram(IncomeFragment())
                     true
                }
                R.id.mdispute_history -> {
                    setFram(Dispute_reportFragment())
                    true
                }
                R.id.mbankDetails -> {
                    setFram(Bank_detailsFragment())
                     true
                }
                R.id.mfund_request_report -> {
                    setFram(Fund_Request_ReportFragment())
                     true
                }*//*

                R.id.mchange_pass -> {
                    setFram(ChangePassFragment())
                    true
                }
                R.id.mlogout -> {
                    startActivity(Intent(this@DashboardActivity,Login_Activity::class.java))
                    pref.writeStringPreference(
                        Var.PREF_IsLogin,"0"
                    )
                    pref.logout(true)
                    true
                }
                    //   loadFragment(new MainActivity());

                else -> {
                        setFram(Home_Fragment())
                        true}
            }
        }
    }
*/
//           startActivity(Intent(this@DashboardActivity,MainActivity::class.java)

    @SuppressLint("WrongConstant")
        fun setFram(fram: Fragment)
        {
    // initial transaction should be wrapped like this
            mainBinding.drawerLayout.closeDrawer(Gravity.START, true);
            val newFragment = fram
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, newFragment, "fragmente")
                .addToBackStack(null)
                .commitAllowingStateLoss()
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

/*
    fun set_details()
    {


        val navHeaderView: View = mainBinding.navigationView.inflateHeaderView(R.layout.navigation_header)
        var  headerUserName:TextView = navHeaderView.findViewById(R.id.user_name_text)
        var headerMobileNo = navHeaderView.findViewById(R.id.user_number_text) as TextView
        headerUserName.setText(pref.ReadStringPreferences(Var.membername)+" ")
        headerMobileNo.setText(pref.ReadStringPreferences(Var.PREF_mobile)+" ")

//        mainBinding.navigationView.user_name_text.text = pref.ReadStringPreferences(Var.membername)+" "
//        mainBinding.navigationView.user_number_text.text = pref.ReadStringPreferences(Var.PREF_mobile)+" "

    }
*/

@SuppressLint("WrongConstant")
fun setnavigation_item()
    {
        mainBinding.lHome.setOnClickListener {
//            setFram(Home_Fragment()
                    mainBinding.drawerLayout.closeDrawer(Gravity.START, true);
            val newFragment = Home_Fragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, newFragment, "home")
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
        mainBinding.lLogout.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, Login_Activity::class.java))
            pref.writeStringPreference(
                Var.PREF_IsLogin, "0"
            )
            pref.logout(true)
        }


        mainBinding.tAccountno.setText(pref.ReadStringPreferences(Var.PREF_Login_Id) + " ")
        mainBinding.tName.setText(pref.ReadStringPreferences(Var.PREF_name) + " ")

        Picasso.with(this@DashboardActivity)
            .load(pref.ReadStringPreferences(Var.PREF_user_image))
            .into(mainBinding.iImage)


        mainBinding.lChangepassword.setOnClickListener {
            setFram(ChangePassFragment())
        }
        mainBinding.lMyPassbook.setOnClickListener {
            setFram(PassbookFragment())
        }

        mainBinding.lProfileLayout.setOnClickListener {
            mainBinding.drawerLayout.closeDrawer(Gravity.START, true);
            mainBinding.navigation.menu.findItem(R.id.navigation_profile).isChecked = true
            setFram(Profile_Fragment())
        }

        mainBinding.lWithrawInBank.setOnClickListener {

            setFram(widrawFragment())
        }

        mainBinding.lRefer.setOnClickListener {

            var data:String="Hey check out this amazing app and Earn money. Use my refer " +
                    "https://www.therippco.com/registration.aspx?ref="+pref.ReadStringPreferences(Var.PREF_Login_Id)
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, data.toString())
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }

        mainBinding.addMember.setOnClickListener {

            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.therippco.com/")
            startActivity(openURL)
        }
    }

/*
    @SuppressLint("WrongConstant")
    override fun onBackPressed() {
        super.onBackPressed()
        var frag: android.app.Fragment? = fragmentManager.findFragmentByTag("home")

        if(mainBinding.drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            mainBinding.drawerLayout.closeDrawer(Gravity.START, true)
        }
        else if(frag==null)
        {
            exit_dialog()
            Log.e("@@", "fragmente_if")
        }
        else
        {
            Log.e("@@", "fragmente_else")
        }
    }
*/

    fun genrate_qr_code()
    {
        val content = pref.ReadStringPreferences(Var.PREF_Login_Id)

        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 512, 512)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }
        mainBinding.iQrcode.setImageBitmap(bitmap)
    }

    fun exit_dialog()
    {
        val builder = AlertDialog.Builder(this,android.app.AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
        //set title for alert dialog
        builder.setTitle("Exit")
        //set message for alert dialog
        builder.setMessage("Do you want to Exit.")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
            builder.setPositiveButton("Yes"){dialogInterface, which ->
            Toast.makeText(applicationContext,"Exit....",Toast.LENGTH_LONG).show()
            val homeIntent = Intent(Intent.ACTION_MAIN)
            homeIntent.addCategory(Intent.CATEGORY_HOME)
            homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(homeIntent)
        }

        builder.setNegativeButton("No"){dialogInterface, which ->

        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}