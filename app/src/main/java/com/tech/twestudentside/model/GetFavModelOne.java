package com.tech.twestudentside.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetFavModelOne {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("tutor_id")
    @Expose
    private String tutorId;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("details")
    @Expose
    private GetFavModelOneDetails details;
    @SerializedName("tutor_details")
    @Expose
    private GetFavModelTutorDetailsFav tutorDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public GetFavModelOneDetails getDetails() {
        return details;
    }

    public void setDetails(GetFavModelOneDetails details) {
        this.details = details;
    }

    public GetFavModelTutorDetailsFav getTutorDetails() {
        return tutorDetails;
    }

    public void setTutorDetails(GetFavModelTutorDetailsFav tutorDetails) {
        this.tutorDetails = tutorDetails;
    }
}
