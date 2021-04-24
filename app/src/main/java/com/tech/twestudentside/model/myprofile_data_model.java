package com.tech.twestudentside.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class myprofile_data_model {
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("address_details")
    @Expose
    private AddressDetails addressDetails;
    @SerializedName("certifiateimage")
    @Expose
    private String certifiateimage;
    @SerializedName("certifiateimage2")
    @Expose
    private String certifiateimage2;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    private String id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("last_name")
    @Expose
    private String lastName;
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
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("register_id")
    @Expose
    private String registerId;
    @SerializedName("social_id")
    @Expose
    private String socialId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("user_details")
    @Expose
    private UserDetails_model userDetails;
    @SerializedName("username")
    @Expose
    private String username;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName2) {
        this.firstName = firstName2;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName2) {
        this.lastName = lastName2;
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

    public String getProfileImage() {
        return this.profileImage;
    }

    public void setProfileImage(String profileImage2) {
        this.profileImage = profileImage2;
    }

    public String getCertifiateimage() {
        return this.certifiateimage;
    }

    public void setCertifiateimage(String certifiateimage3) {
        this.certifiateimage = certifiateimage3;
    }

    public String getCertifiateimage2() {
        return this.certifiateimage2;
    }

    public void setCertifiateimage2(String certifiateimage22) {
        this.certifiateimage2 = certifiateimage22;
    }

    public UserDetails_model getUserDetails() {
        return this.userDetails;
    }

    public void setUserDetails(UserDetails_model userDetails2) {
        this.userDetails = userDetails2;
    }

    public AddressDetails getAddressDetails() {
        return this.addressDetails;
    }

    public void setAddressDetails(AddressDetails addressDetails2) {
        this.addressDetails = addressDetails2;
    }
}
