package com.tech.twestudentside.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCalculaterModeldata {
    @SerializedName("full_course")
    @Expose
    private FullCourse fullCourse;
    @SerializedName("full_course_time")
    @Expose
    private String fullCourseTime;
    @SerializedName("full_course_time_group")
    @Expose
    private String fullCourseTimeGroup;
    @SerializedName("hourly_details")
    @Expose
    private HourlyDetails hourlyDetails;
    @SerializedName("monthly_details")
    @Expose
    private MonthlyDetails monthlyDetails;
    @SerializedName("per_hour_payment")
    @Expose
    private String perHourPayment;
    @SerializedName("per_hour_payment_group")
    @Expose
    private String perHourPaymentGroup;
    @SerializedName("weekly_details")
    @Expose
    private WeeklyDetails weeklyDetails;

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

    public HourlyDetails getHourlyDetails() {
        return this.hourlyDetails;
    }

    public void setHourlyDetails(HourlyDetails hourlyDetails2) {
        this.hourlyDetails = hourlyDetails2;
    }

    public WeeklyDetails getWeeklyDetails() {
        return this.weeklyDetails;
    }

    public void setWeeklyDetails(WeeklyDetails weeklyDetails2) {
        this.weeklyDetails = weeklyDetails2;
    }

    public MonthlyDetails getMonthlyDetails() {
        return this.monthlyDetails;
    }

    public void setMonthlyDetails(MonthlyDetails monthlyDetails2) {
        this.monthlyDetails = monthlyDetails2;
    }

    public FullCourse getFullCourse() {
        return this.fullCourse;
    }

    public void setFullCourse(FullCourse fullCourse2) {
        this.fullCourse = fullCourse2;
    }
}
