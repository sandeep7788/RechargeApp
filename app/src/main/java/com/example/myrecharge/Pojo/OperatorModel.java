package com.example.myrecharge.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OperatorModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("OperatorID")
    @Expose
    private Integer operatorID;
    @SerializedName("OperatorName")
    @Expose
    private String operatorName;
    @SerializedName("OperatorCode")
    @Expose
    private String operatorCode;
    @SerializedName("operaotimageurl")
    @Expose
    private String operaotimageurl;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Integer operatorID) {
        this.operatorID = operatorID;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getOperaotimageurl() {
        return operaotimageurl;
    }

    public void setOperaotimageurl(String operaotimageurl) {
        this.operaotimageurl = operaotimageurl;
    }

}