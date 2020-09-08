package com.example.myrecharge.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelPassbook_Att  {

    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("ttype")
    @Expose
    private String ttype;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("balance")
    @Expose
    private Double balance;
    @SerializedName("icon")
    @Expose
    private String icon;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTtype() {
        return ttype;
    }

    public void setTtype(String ttype) {
        this.ttype = ttype;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}