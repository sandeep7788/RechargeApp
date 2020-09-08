package com.example.myrecharge.Helper

import android.database.Observable
import com.example.myrecharge.Pojo.Model_Login
import com.example.myrecharge.Pojo.OperatorModel
import com.google.gson.JsonArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class Services
{
//https://therippco.com/api/common.ashx?f=auth&loginid=5002081557&password=babydoll&ip=
    var login_api: Login_api? = null
    fun getlogin(): Login_api? {
        if (login_api == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://therippco.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            login_api = retrofit.create(Login_api::class.java)
        }
        return login_api
    }

    var getOperator: OperatorList? = null
    fun getOperator1(): OperatorList? {
        if (getOperator == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://paymyrecharge.in/APIMANAGER/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            getOperator = retrofit.create(OperatorList::class.java)
        }
        return getOperator
    }

    interface Login_api {
        @POST("common.ashx?")
        @FormUrlEncoded
        fun Login_m(
            @Field("f") f: String?,
            @Field("loginid") loginid: String?,
            @Field("password") password: String?,
            @Field("ip") ip: String?
        ): Call<Model_Login?>?
    }
//    https://paymyrecharge.in/APIMANAGER/oparetor.aspx?Servertype=1
    interface OperatorList {
        @FormUrlEncoded
        @POST("oparetor.aspx?")
        fun getOperatorList(
            @Field("Servertype") Servertype: Int?
        ): Call<JsonArray>
    }
}