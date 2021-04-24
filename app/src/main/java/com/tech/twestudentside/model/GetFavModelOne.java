package com.tech.twestudentside.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetFavModelOne {
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("details")
    @Expose
    private GetFavModelOneDetails details;
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    private String f451id;
    @SerializedName("tutor_details")
    @Expose
    private GetFavModelTutorDetailsFav tutorDetails;
    @SerializedName("tutor_id")
    @Expose
    private String tutorId;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public String getId() {
        return this.f451id;
    }

    public void setId(String id) {
        this.f451id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId2) {
        this.userId = userId2;
    }

    public String getTutorId() {
        return this.tutorId;
    }

    public void setTutorId(String tutorId2) {
        this.tutorId = tutorId2;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime2) {
        this.dateTime = dateTime2;
    }

    public GetFavModelOneDetails getDetails() {
        return this.details;
    }

    public void setDetails(GetFavModelOneDetails details2) {
        this.details = details2;
    }

    public GetFavModelTutorDetailsFav getTutorDetails() {
        return this.tutorDetails;
    }

    public void setTutorDetails(GetFavModelTutorDetailsFav tutorDetails2) {
        this.tutorDetails = tutorDetails2;
    }
}
