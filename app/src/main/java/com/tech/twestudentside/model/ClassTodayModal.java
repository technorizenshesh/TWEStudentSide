package com.tech.twestudentside.model;

public class ClassTodayModal {
    private String name;
    private String orderId;
    private String payment;
    private String rating;

    public ClassTodayModal(String name2, String orderId2, String rating2, String payment2) {
        this.name = name2;
        this.orderId = orderId2;
        this.rating = rating2;
        this.payment = payment2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId2) {
        this.orderId = orderId2;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating2) {
        this.rating = rating2;
    }

    public String getPayment() {
        return this.payment;
    }

    public void setPayment(String payment2) {
        this.payment = payment2;
    }
}
