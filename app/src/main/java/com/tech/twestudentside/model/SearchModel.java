package com.tech.twestudentside.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<SearchDataModel> result = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<SearchDataModel> getResult() {
        return this.result;
    }

    public void setResult(List<SearchDataModel> result2) {
        this.result = result2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status2) {
        this.status = status2;
    }
}
