package com.tech.twestudentside.model;

public class pricelistModel {

    String Day;
    String Discount;

    public pricelistModel(String day, String discount) {
        Day = day;
        Discount = discount;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
}
