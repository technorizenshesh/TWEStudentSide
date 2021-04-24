package com.tech.twestudentside.model;

public class pricelistModel {
    String Day;
    String Discount;

    public pricelistModel(String day, String discount) {
        this.Day = day;
        this.Discount = discount;
    }

    public String getDay() {
        return this.Day;
    }

    public void setDay(String day) {
        this.Day = day;
    }

    public String getDiscount() {
        return this.Discount;
    }

    public void setDiscount(String discount) {
        this.Discount = discount;
    }
}
