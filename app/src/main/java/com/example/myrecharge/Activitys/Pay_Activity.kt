package com.example.myrecharge.Activitys

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.budiyev.android.codescanner.*
import com.example.myrecharge.Helper.*
import com.example.myrecharge.Pojo.ModelGetUser
import com.example.myrecharge.R
import com.example.myrecharge.databinding.ActivityPayBinding
import retrofit2.Call
import retrofit2.Response


class Pay_Activity : AppCompatActivity()
{
    lateinit var mainBinding : ActivityPayBinding
    var pref= Local_data(this@Pay_Activity)
    private lateinit var codeScanner: CodeScanner
    private val CAMERA_PERMISSION_CODE = 100
    private val STORAGE_PERMISSION_CODE = 101
    val REQUEST_ID_MULTIPLE_PERMISSIONS = 7

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        mainBinding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_pay
            )
        pref.setMyappContext(this@Pay_Activity)
        start_scaner()
        checkAndRequestPermissions()

    mainBinding.btnBack.setOnClickListener { finish() }

        mainBinding.btnNaxt.setOnClickListener {
            if(mainBinding.edtID.text.toString().isEmpty())
            {
                mainBinding.edtID.setError("Enter value")
            }
            else
            {
                mainBinding.tLabel.visibility=View.GONE
                call_API(mainBinding.edtID.text.toString())
            }
        }



    }

    private fun checkAndRequestPermissions(): Boolean {
        val camera = ContextCompat.checkSelfPermission(
            this@Pay_Activity,
            Manifest.permission.CAMERA
        )
        val wtite = ContextCompat.checkSelfPermission(
            this@Pay_Activity,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val read = ContextCompat.checkSelfPermission(
            this@Pay_Activity,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (wtite != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (read != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                this@Pay_Activity,
                listPermissionsNeeded.toTypedArray(),
                REQUEST_ID_MULTIPLE_PERMISSIONS
            )
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        Log.d("in fragment on request", "Permission callback called-------")
        when (requestCode) {
            REQUEST_ID_MULTIPLE_PERMISSIONS -> {
                val perms: MutableMap<String, Int> = HashMap()
                // Initialize the map with both permissions
                perms[Manifest.permission.WRITE_EXTERNAL_STORAGE] =
                    PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.CAMERA] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.READ_EXTERNAL_STORAGE] = PackageManager.PERMISSION_GRANTED
                // Fill with actual results from user
                if (grantResults.size > 0) {
                    var i = 0
                    while (i < permissions.size) {
                        perms[permissions[i]] = grantResults[i]
                        i++
                    }
                    // Check for both permissions
                    if (perms[Manifest.permission.WRITE_EXTERNAL_STORAGE] == PackageManager.PERMISSION_GRANTED && perms[Manifest.permission.CAMERA] == PackageManager.PERMISSION_GRANTED && perms[Manifest.permission.READ_EXTERNAL_STORAGE] == PackageManager.PERMISSION_GRANTED
                    ) {
                        Log.d(
                            "in fragment on request",
                            "CAMERA & WRITE_EXTERNAL_STORAGE READ_EXTERNAL_STORAGE permission granted"
                        )
                        // process the normal flow
                        //else any one or both the permissions are not granted
                    } else {
                        Log.d(
                            "in fragment on request",
                            "Some permissions are not granted ask again "
                        )
                        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
//                        // shouldShowRequestPermissionRationale will return true
                        //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                        if (ActivityCompat.shouldShowRequestPermissionRationale(
                                this@Pay_Activity,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            ) || ActivityCompat.shouldShowRequestPermissionRationale(
                                this@Pay_Activity,
                                Manifest.permission.CAMERA
                            ) || ActivityCompat.shouldShowRequestPermissionRationale(
                                this@Pay_Activity,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                            )
                        ) {
                            showDialogOK("Camera and Storage Permission required for this app",
                                DialogInterface.OnClickListener { dialog, which ->
                                    when (which) {
                                        DialogInterface.BUTTON_POSITIVE -> checkAndRequestPermissions()
                                        DialogInterface.BUTTON_NEGATIVE -> {
                                        }
                                    }
                                })
                        } else {
                            Toast.makeText(
                                this@Pay_Activity,
                                "Go to settings and enable permissions",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            //                            //proceed with logic by disabling the related features or quit the app.
                        }
                    }
                }
            }
        }

    }

    private fun showDialogOK(
        message: String,
        okListener: DialogInterface.OnClickListener
    ) {
        AlertDialog.Builder(this@Pay_Activity)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", okListener)
            .create()
            .show()
    }
    fun check()
    {
        if(ContextCompat.checkSelfPermission(this@Pay_Activity,
                Manifest.permission.WRITE_CALENDAR)
            != PackageManager.PERMISSION_GRANTED)
        {
            // Permission is not granted

        }
        else
        {
            check()
        }
    }

    fun call_API(id_string:String)
    {
        var pref= Local_data(this@Pay_Activity)
        var methods= Method_collection(this@Pay_Activity)
        methods.show_Progress_dialog()

        pref.setMyappContext(this@Pay_Activity)

        val apiInterface: ApiInterface =
            RetrofitManager.instance!!.create(ApiInterface::class.java)

        val call: Call<ModelGetUser?>? = apiInterface.getUser("getuser",id_string)
        call!!.enqueue(object : retrofit2.Callback<ModelGetUser?>
        {
            override fun onResponse(call: Call<ModelGetUser?>, response: Response<ModelGetUser?>) {
                methods.dismis_Progress_dialog()

                try {


                    if (response.isSuccessful) {
                        if (response.body()!!.status.toString().toLowerCase().equals("success")) {

                            Toast.makeText(
                                this@Pay_Activity,response.body()!!.message.toString()+" ",
                                Toast.LENGTH_SHORT
                            ).show()
                            if(response!!.body()!!.data!=null)
                            {
                                if (response.body()!!.data.get(0).memberId.toString().trim().equals(
                                        pref.ReadStringPreferences(Var.PREF_Login_Id).toString().trim()
                                    )
                                ) {
                                    mainBinding.tLabel.visibility = View.VISIBLE
                                } else {

                                    Log.e("@@", response!!.body()!!.data.get(0).memberId.toString() + " ")
                                    Log.e("@@", response!!.body()!!.data.get(0).memberName.toString() + " ")
                                    var intent = Intent(this@Pay_Activity, Pay_Activity2::class.java)
                                    intent.putExtra(
                                        "id",
                                        response.body()!!.data.get(0).memberId.toString()
                                    )
                                    intent.putExtra(
                                        "name",
                                        response.body()!!.data.get(0).memberName.toString()
                                    )
//                        intent.putExtra("amount",response.body()!!.data.get(0)..toString())
                                    startActivity(intent)
                                }

                            }


                        }
                    }
                }
                catch (e:Exception)
                {
                    Toast.makeText(this@Pay_Activity," "+e.message.toString()+" ",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ModelGetUser?>, t: Throwable) {
                methods.dismis_Progress_dialog()
                Toast.makeText(this@Pay_Activity," "+t.message.toString(),Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun start_scaner()
    {
        val scannerView = mainBinding.scannerView

        codeScanner = CodeScanner(this, scannerView)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_LONG).show()

                call_API(it.text.toString())

            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}