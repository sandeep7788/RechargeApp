package com.example.myrecharge.Pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelRechargee {

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
    private List<ModelRecharge_Data> data = null;

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

    public List<ModelRecharge_Data> getData() {
        return data;
    }

    public void setData(List<ModelRecharge_Data> data) {
        this.data = data;
    }

}
