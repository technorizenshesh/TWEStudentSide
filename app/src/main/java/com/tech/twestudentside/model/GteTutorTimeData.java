package com.tech.twestudentside.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GteTutorTimeData {
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("affiliations")
    @Expose
    private String affiliations;
    @SerializedName("award")
    @Expose
    private String award;
    @SerializedName("certifiateimage")
    @Expose
    private String certifiateimage;
    @SerializedName("certifiateimage2")
    @Expose
    private String certifiateimage2;
    @SerializedName("certification")
    @Expose
    private String certification;
    @SerializedName("check_status")
    @Expose
    private String checkStatus;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("education")
    @Expose
    private String education;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("friday")
    @Expose
    private String friday;
    @SerializedName("full_course_time")
    @Expose
    private String fullCourseTime;
    @SerializedName("full_course_time_group")
    @Expose
    private String fullCourseTimeGroup;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    private String id;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("monday")
    @Expose
    private String monday;
    @SerializedName("per_hour_payment")
    @Expose
    private String perHourPayment;
    @SerializedName("per_hour_payment_group")
    @Expose
    private String perHourPaymentGroup;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("saturday")
    @Expose
    private String saturday;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("sunday")
    @Expose
    private String sunday;
    @SerializedName("teach_distance")
    @Expose
    private String teachDistance;
    @SerializedName("thursday")
    @Expose
    private String thursday;
    @SerializedName("time_slot")
    @Expose
    private String timeSlot;
    @SerializedName("time_zone")
    @Expose
    private String timeZone;
    @SerializedName("tuesday")
    @Expose
    private String tuesday;
    @SerializedName("tutorcategory")
    @Expose
    private String tutorcategory;
    @SerializedName("tutorsubcategory")
    @Expose
    private String tutorsubcategory;
    @SerializedName("tutorsubject")
    @Expose
    private String tutorsubject;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("wednesday")
    @Expose
    private String wednesday;
    @SerializedName("where_to_teach")
    @Expose
    private String whereToTeach;

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

    public String getProfileImage() {
        return this.profileImage;
    }

    public void setProfileImage(String profileImage2) {
        this.profileImage = profileImage2;
    }

    public String getAbout() {
        return this.about;
    }

    public void setAbout(String about2) {
        this.about = about2;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location2) {
        this.location = location2;
    }

    public String getDob() {
        return this.dob;
    }

    public void setDob(String dob2) {
        this.dob = dob2;
    }

    public String getTutorcategory() {
        return this.tutorcategory;
    }

    public void setTutorcategory(String tutorcategory2) {
        this.tutorcategory = tutorcategory2;
    }

    public String getTutorsubcategory() {
        return this.tutorsubcategory;
    }

    public void setTutorsubcategory(String tutorsubcategory2) {
        this.tutorsubcategory = tutorsubcategory2;
    }

    public String getTutorsubject() {
        return this.tutorsubject;
    }

    public void setTutorsubject(String tutorsubject2) {
        this.tutorsubject = tutorsubject2;
    }

    public String getEducation() {
        return this.education;
    }

    public void setEducation(String education2) {
        this.education = education2;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender2) {
        this.gender = gender2;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language2) {
        this.language = language2;
    }

    public String getAward() {
        return this.award;
    }

    public void setAward(String award2) {
        this.award = award2;
    }

    public String getCertification() {
        return this.certification;
    }

    public void setCertification(String certification2) {
        this.certification = certification2;
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

    public String getAffiliations() {
        return this.affiliations;
    }

    public void setAffiliations(String affiliations2) {
        this.affiliations = affiliations2;
    }

    public String getWhereToTeach() {
        return this.whereToTeach;
    }

    public void setWhereToTeach(String whereToTeach2) {
        this.whereToTeach = whereToTeach2;
    }

    public String getTeachDistance() {
        return this.teachDistance;
    }

    public void setTeachDistance(String teachDistance2) {
        this.teachDistance = teachDistance2;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime2) {
        this.startTime = startTime2;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime2) {
        this.endTime = endTime2;
    }

    public String getTimeSlot() {
        return this.timeSlot;
    }

    public void setTimeSlot(String timeSlot2) {
        this.timeSlot = timeSlot2;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String timeZone2) {
        this.timeZone = timeZone2;
    }

    public String getMonday() {
        return this.monday;
    }

    public void setMonday(String monday2) {
        this.monday = monday2;
    }

    public String getTuesday() {
        return this.tuesday;
    }

    public void setTuesday(String tuesday2) {
        this.tuesday = tuesday2;
    }

    public String getWednesday() {
        return this.wednesday;
    }

    public void setWednesday(String wednesday2) {
        this.wednesday = wednesday2;
    }

    public String getThursday() {
        return this.thursday;
    }

    public void setThursday(String thursday2) {
        this.thursday = thursday2;
    }

    public String getFriday() {
        return this.friday;
    }

    public void setFriday(String friday2) {
        this.friday = friday2;
    }

    public String getSaturday() {
        return this.saturday;
    }

    public void setSaturday(String saturday2) {
        this.saturday = saturday2;
    }

    public String getSunday() {
        return this.sunday;
    }

    public void setSunday(String sunday2) {
        this.sunday = sunday2;
    }

    public String getPerHourPayment() {
        return this.perHourPayment;
    }

    public void setPerHourPayment(String perHourPayment2) {
        this.perHourPayment = perHourPayment2;
    }

    public String getFullCourseTime() {
        return this.fullCourseTime;
    }

    public void setFullCourseTime(String fullCourseTime2) {
        this.fullCourseTime = fullCourseTime2;
    }

    public String getPerHourPaymentGroup() {
        return this.perHourPaymentGroup;
    }

    public void setPerHourPaymentGroup(String perHourPaymentGroup2) {
        this.perHourPaymentGroup = perHourPaymentGroup2;
    }

    public String getFullCourseTimeGroup() {
        return this.fullCourseTimeGroup;
    }

    public void setFullCourseTimeGroup(String fullCourseTimeGroup2) {
        this.fullCourseTimeGroup = fullCourseTimeGroup2;
    }

    public String getCheckStatus() {
        return this.checkStatus;
    }

    public void setCheckStatus(String checkStatus2) {
        this.checkStatus = checkStatus2;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime2) {
        this.dateTime = dateTime2;
    }
}
