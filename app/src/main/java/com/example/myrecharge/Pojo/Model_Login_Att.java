package com.example.myrecharge.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model_Login_Att  {

    @SerializedName("loginid")
    @Expose
    private String loginid;
    @SerializedName("membername")
    @Expose
    private String membername;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("balance")
    @Expose
    private Double balance;
    @SerializedName("balance2")
    @Expose
    private Double balance2;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("totaldownline")
    @Expose
    private Integer totaldownline;
    @SerializedName("totaldirect")
    @Expose
    private Integer totaldirect;
    @SerializedName("rankimage")
    @Expose
    private String rankimage;

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBalance2() {
        return balance2;
    }

    public void setBalance2(Double balance2) {
        this.balance2 = balance2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getTotaldownline() {
        return totaldownline;
    }

    public void setTotaldownline(Integer totaldownline) {
        this.totaldownline = totaldownline;
    }

    public Integer getTotaldirect() {
        return totaldirect;
    }

    public void setTotaldirect(Integer totaldirect) {
        this.totaldirect = totaldirect;
    }

    public String getRankimage() {
        return rankimage;
    }

    public void setRankimage(String rankimage) {
        this.rankimage = rankimage;
    }

}