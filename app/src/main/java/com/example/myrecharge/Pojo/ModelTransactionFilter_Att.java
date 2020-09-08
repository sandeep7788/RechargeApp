package com.example.myrecharge.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelTransactionFilter_Att {

    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Sponsorid")
    @Expose
    private String sponsorid;
    @SerializedName("SponsorName")
    @Expose
    private String sponsorName;
    @SerializedName("DOJ")
    @Expose
    private String dOJ;
    @SerializedName("Package")
    @Expose
    private String _package;
    @SerializedName("TotalInvestment")
    @Expose
    private Integer totalInvestment;
    @SerializedName("Mobile")
    @Expose
    private Object mobile;
    @SerializedName("Email")
    @Expose
    private Object email;
    @SerializedName("Flag")
    @Expose
    private String flag;
    @SerializedName("Leg")
    @Expose
    private Object leg;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("CountryFlag")
    @Expose
    private Object countryFlag;
    @SerializedName("PaidDt")
    @Expose
    private Object paidDt;
    @SerializedName("Status")
    @Expose
    private Object status;
    @SerializedName("LevelNo")
    @Expose
    private String levelNo;

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSponsorid() {
        return sponsorid;
    }

    public void setSponsorid(String sponsorid) {
        this.sponsorid = sponsorid;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getDOJ() {
        return dOJ;
    }

    public void setDOJ(String dOJ) {
        this.dOJ = dOJ;
    }

    public String getPackage() {
        return _package;
    }

    public void setPackage(String _package) {
        this._package = _package;
    }

    public Integer getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(Integer totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public Object getMobile() {
        return mobile;
    }

    public void setMobile(Object mobile) {
        this.mobile = mobile;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Object getLeg() {
        return leg;
    }

    public void setLeg(Object leg) {
        this.leg = leg;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Object getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(Object countryFlag) {
        this.countryFlag = countryFlag;
    }

    public Object getPaidDt() {
        return paidDt;
    }

    public void setPaidDt(Object paidDt) {
        this.paidDt = paidDt;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public String getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(String levelNo) {
        this.levelNo = levelNo;
    }

}