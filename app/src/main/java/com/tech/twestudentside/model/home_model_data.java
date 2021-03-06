package com.tech.twestudentside.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class home_model_data {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("tutorcategory")
    @Expose
    private String tutorcategory;
    @SerializedName("tutorsubcategory")
    @Expose
    private String tutorsubcategory;
    @SerializedName("tutorsubject")
    @Expose
    private String tutorsubject;
    @SerializedName("education")
    @Expose
    private String education;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("award")
    @Expose
    private String award;
    @SerializedName("certification")
    @Expose
    private String certification;
    @SerializedName("certifiateimage")
    @Expose
    private String certifiateimage;
    @SerializedName("certifiateimage2")
    @Expose
    private String certifiateimage2;
    @SerializedName("affiliations")
    @Expose
    private String affiliations;
    @SerializedName("where_to_teach")
    @Expose
    private String whereToTeach;
    @SerializedName("teach_distance")
    @Expose
    private String teachDistance;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("time_slot")
    @Expose
    private String timeSlot;
    @SerializedName("time_zone")
    @Expose
    private String timeZone;
    @SerializedName("monday")
    @Expose
    private String monday;
    @SerializedName("tuesday")
    @Expose
    private String tuesday;
    @SerializedName("wednesday")
    @Expose
    private String wednesday;
    @SerializedName("thursday")
    @Expose
    private String thursday;
    @SerializedName("friday")
    @Expose
    private String friday;
    @SerializedName("saturday")
    @Expose
    private String saturday;
    @SerializedName("sunday")
    @Expose
    private String sunday;
    @SerializedName("per_hour_payment")
    @Expose
    private String perHourPayment;
    @SerializedName("full_course_time")
    @Expose
    private String fullCourseTime;
    @SerializedName("per_hour_payment_group")
    @Expose
    private String perHourPaymentGroup;
    @SerializedName("full_course_time_group")
    @Expose
    private String fullCourseTimeGroup;
    @SerializedName("check_status")
    @Expose
    private String checkStatus;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("total_charges_individual")
    @Expose
    private Integer totalChargesIndividual;
    @SerializedName("total_charges_group")
    @Expose
    private Integer totalChargesGroup;
    @SerializedName("tutor_details")
    @Expose
    private TutorDetailsModel tutorDetails;

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

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getTutorcategory() {
        return tutorcategory;
    }

    public void setTutorcategory(String tutorcategory) {
        this.tutorcategory = tutorcategory;
    }

    public String getTutorsubcategory() {
        return tutorsubcategory;
    }

    public void setTutorsubcategory(String tutorsubcategory) {
        this.tutorsubcategory = tutorsubcategory;
    }

    public String getTutorsubject() {
        return tutorsubject;
    }

    public void setTutorsubject(String tutorsubject) {
        this.tutorsubject = tutorsubject;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getCertifiateimage() {
        return certifiateimage;
    }

    public void setCertifiateimage(String certifiateimage) {
        this.certifiateimage = certifiateimage;
    }

    public String getCertifiateimage2() {
        return certifiateimage2;
    }

    public void setCertifiateimage2(String certifiateimage2) {
        this.certifiateimage2 = certifiateimage2;
    }

    public String getAffiliations() {
        return affiliations;
    }

    public void setAffiliations(String affiliations) {
        this.affiliations = affiliations;
    }

    public String getWhereToTeach() {
        return whereToTeach;
    }

    public void setWhereToTeach(String whereToTeach) {
        this.whereToTeach = whereToTeach;
    }

    public String getTeachDistance() {
        return teachDistance;
    }

    public void setTeachDistance(String teachDistance) {
        this.teachDistance = teachDistance;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getPerHourPayment() {
        return perHourPayment;
    }

    public void setPerHourPayment(String perHourPayment) {
        this.perHourPayment = perHourPayment;
    }

    public String getFullCourseTime() {
        return fullCourseTime;
    }

    public void setFullCourseTime(String fullCourseTime) {
        this.fullCourseTime = fullCourseTime;
    }

    public String getPerHourPaymentGroup() {
        return perHourPaymentGroup;
    }

    public void setPerHourPaymentGroup(String perHourPaymentGroup) {
        this.perHourPaymentGroup = perHourPaymentGroup;
    }

    public String getFullCourseTimeGroup() {
        return fullCourseTimeGroup;
    }

    public void setFullCourseTimeGroup(String fullCourseTimeGroup) {
        this.fullCourseTimeGroup = fullCourseTimeGroup;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Integer getTotalChargesIndividual() {
        return totalChargesIndividual;
    }

    public void setTotalChargesIndividual(Integer totalChargesIndividual) {
        this.totalChargesIndividual = totalChargesIndividual;
    }

    public Integer getTotalChargesGroup() {
        return totalChargesGroup;
    }

    public void setTotalChargesGroup(Integer totalChargesGroup) {
        this.totalChargesGroup = totalChargesGroup;
    }

    public TutorDetailsModel getTutorDetails() {
        return tutorDetails;
    }

    public void setTutorDetails(TutorDetailsModel tutorDetails) {
        this.tutorDetails = tutorDetails;
    }


}
