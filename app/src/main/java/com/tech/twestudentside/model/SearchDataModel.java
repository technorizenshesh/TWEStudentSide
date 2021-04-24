package com.tech.twestudentside.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchDataModel {
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    private String id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("register_id")
    @Expose
    private String registerId;
    @SerializedName("social_id")
    @Expose
    private String socialId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tutor_details")
    @Expose
    private SearchModelTutorDetails tutorDetails;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("username")
    @Expose
    private String username;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username2) {
        this.username = username2;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile2) {
        this.mobile = mobile2;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email2) {
        this.email = email2;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password2) {
        this.password = password2;
    }

    public String getOtp() {
        return this.otp;
    }

    public void setOtp(String otp2) {
        this.otp = otp2;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image2) {
        this.image = image2;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address2) {
        this.address = address2;
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

    public String getRegisterId() {
        return this.registerId;
    }

    public void setRegisterId(String registerId2) {
        this.registerId = registerId2;
    }

    public String getSocialId() {
        return this.socialId;
    }

    public void setSocialId(String socialId2) {
        this.socialId = socialId2;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type2) {
        this.type = type2;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status2) {
        this.status = status2;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime2) {
        this.dateTime = dateTime2;
    }

    public SearchModelTutorDetails getTutorDetails() {
        return this.tutorDetails;
    }

    public void setTutorDetails(SearchModelTutorDetails tutorDetails2) {
        this.tutorDetails = tutorDetails2;
    }
}
