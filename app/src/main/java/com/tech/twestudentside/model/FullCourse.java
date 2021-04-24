package com.tech.twestudentside.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FullCourse {
    @SerializedName("discount_to_student")
    @Expose
    private Integer discountToStudent;
    @SerializedName("service_charge")
    @Expose
    private Integer serviceCharge;
    @SerializedName("student_price")
    @Expose
    private Integer studentPrice;
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

    public Integer getDiscountToStudent() {
        return this.discountToStudent;
    }

    public void setDiscountToStudent(Integer discountToStudent2) {
        this.discountToStudent = discountToStudent2;
    }

    public Integer getTeachersEarning() {
        return this.teachersEarning;
    }

    public void setTeachersEarning(Integer teachersEarning2) {
        this.teachersEarning = teachersEarning2;
    }

    public Integer getStudentPrice() {
        return this.studentPrice;
    }

    public void setStudentPrice(Integer studentPrice2) {
        this.studentPrice = studentPrice2;
    }

    public Integer getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Integer totalPrice2) {
        this.totalPrice = totalPrice2;
    }
}
