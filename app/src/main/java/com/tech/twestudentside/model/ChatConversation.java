package com.tech.twestudentside.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ChatConversation {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<ChatConversationData> result = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<ChatConversationData> getResult() {
        return this.result;
    }

    public void setResult(List<ChatConversationData> result2) {
        this.result = result2;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status2) {
        this.status = status2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }
}
