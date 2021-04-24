package com.tech.twestudentside.model;

public class SaloonSpecialistModel {
    private boolean isSelected;
    String name;

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public SaloonSpecialistModel(String name2) {
        this.name = name2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }
}
