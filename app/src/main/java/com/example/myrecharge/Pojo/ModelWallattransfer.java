package com.example.myrecharge.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelWallattransfer {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("errorcode")
    @Expose
    private Integer errorcode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<ModelWallattransfer_data> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(Integer errorcode) {
        this.errorcode = errorcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ModelWallattransfer_data> getData() {
        return data;
    }

    public void setData(List<ModelWallattransfer_data> data) {
        this.data = data;
    }

}