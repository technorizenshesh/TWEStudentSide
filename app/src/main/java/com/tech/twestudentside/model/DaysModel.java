package com.tech.twestudentside.model;

public class DaysModel {
    String Day;
    private boolean isSelected = false;

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public DaysModel(String day) {
        this.Day = day;
    }

    public String getDay() {
        return this.Day;
    }

    public void setDay(String day) {
        this.Day = day;
    }
}
