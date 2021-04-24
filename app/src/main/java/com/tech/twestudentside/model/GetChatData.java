package com.tech.twestudentside.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetChatData {
    @SerializedName("chat_audio")
    @Expose
    private String chatAudio;
    @SerializedName("chat_document")
    @Expose
    private String chatDocument;
    @SerializedName("chat_image")
    @Expose
    private String chatImage;
    @SerializedName("chat_message")
    @Expose
    private String chatMessage;
    @SerializedName("chat_video")
    @Expose
    private String chatVideo;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    private String f450id;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;
    @SerializedName("receiver_detail")
    @Expose
    private ReceiverChatModel receiverDetail;
    @SerializedName("receiver_id")
    @Expose
    private String receiverId;
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("sender_detail")
    @Expose
    private SenderDetailModel senderDetail;
    @SerializedName("sender_id")
    @Expose
    private String senderId;
    @SerializedName("status")
    @Expose
    private String status;

    public String getId() {
        return this.f450id;
    }

    public void setId(String id) {
        this.f450id = id;
    }

    public String getSenderId() {
        return this.senderId;
    }

    public void setSenderId(String senderId2) {
        this.senderId = senderId2;
    }

    public String getReceiverId() {
        return this.receiverId;
    }

    public void setReceiverId(String receiverId2) {
        this.receiverId = receiverId2;
    }

    public String getChatMessage() {
        return this.chatMessage;
    }

    public void setChatMessage(String chatMessage2) {
        this.chatMessage = chatMessage2;
    }

    public String getChatImage() {
        return this.chatImage;
    }

    public void setChatImage(String chatImage2) {
        this.chatImage = chatImage2;
    }

    public String getChatAudio() {
        return this.chatAudio;
    }

    public void setChatAudio(String chatAudio2) {
        this.chatAudio = chatAudio2;
    }

    public String getChatVideo() {
        return this.chatVideo;
    }

    public void setChatVideo(String chatVideo2) {
        this.chatVideo = chatVideo2;
    }

    public String getChatDocument() {
        return this.chatDocument;
    }

    public void setChatDocument(String chatDocument2) {
        this.chatDocument = chatDocument2;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String lat2) {
        this.lat = lat2;
    }

    public String getLon() {
        return this.lon;
    }

    public void setLon(String lon2) {
        this.lon = lon2;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status2) {
        this.status = status2;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date2) {
        this.date = date2;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result2) {
        this.result = result2;
    }

    public SenderDetailModel getSenderDetail() {
        return this.senderDetail;
    }

    public void setSenderDetail(SenderDetailModel senderDetail2) {
        this.senderDetail = senderDetail2;
    }

    public ReceiverChatModel getReceiverDetail() {
        return this.receiverDetail;
    }

    public void setReceiverDetail(ReceiverChatModel receiverDetail2) {
        this.receiverDetail = receiverDetail2;
    }
}
