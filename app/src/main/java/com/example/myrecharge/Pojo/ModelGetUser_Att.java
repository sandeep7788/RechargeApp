package com.example.myrecharge.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelGetUser_Att {

    @SerializedName("MemberId")
    @Expose
    private String memberId;
    @SerializedName("MemberName")
    @Expose
    private String memberName;
    @SerializedName("JoinDate")
    @Expose
    private String joinDate;
    @SerializedName("ActiveDate")
    @Expose
    private String activeDate;
    @SerializedName("Country")
    @Expose
    private String country;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(String activeDate) {
        this.activeDate = activeDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}