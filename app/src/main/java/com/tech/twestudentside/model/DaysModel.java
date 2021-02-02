package com.tech.twestudentside.model;

public class DaysModel {

    String Day;

    private boolean isSelected = false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public DaysModel(String day) {
        Day = day;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }
}
