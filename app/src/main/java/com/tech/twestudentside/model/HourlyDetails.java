package com.tech.twestudentside.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HourlyDetails {
    @SerializedName("discount_to_student")
    @Expose
    private String discountToStudent;
    @SerializedName("service_charge")
    @Expose
    private Integer serviceCharge;
    @SerializedName("student_price")
    @Expose
    private String studentPrice;
    @SerializedName("teachers_earning")
    @Expose
    private Integer teachersEarning;
    @SerializedName("total_price")
    @Expose
    private Integer totalPrice;

    public Integer getServiceCharge() {
        return this.serviceCharge;
    }

    public void setServiceCharge(Integer serviceCharge2) {
        this.serviceCharge = serviceCharge2;
    }

    public String getDiscountToStudent() {
        return this.discountToStudent;
    }

    public void setDiscountToStudent(String discountToStudent2) {
        this.discountToStudent = discountToStudent2;
    }

    public Integer getTeachersEarning() {
        return this.teachersEarning;
    }

    public void setTeachersEarning(Integer teachersEarning2) {
        this.teachersEarning = teachersEarning2;
    }

    public String getStudentPrice() {
        return this.studentPrice;
    }

    public void setStudentPrice(String studentPrice2) {
        this.studentPrice = studentPrice2;
    }

    public Integer getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Integer totalPrice2) {
        this.totalPrice = totalPrice2;
    }
}
