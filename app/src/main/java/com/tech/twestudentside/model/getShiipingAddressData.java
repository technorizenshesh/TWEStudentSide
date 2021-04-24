package com.tech.twestudentside.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getShiipingAddressData {
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("address_type")
    @Expose
    private String addressType;
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    private String id;
    private boolean isChecked;
    private boolean isSelected;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId2) {
        this.userId = userId2;
    }

    public String getAddressType() {
        return this.addressType;
    }

    public void setAddressType(String addressType2) {
        this.addressType = addressType2;
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
}
