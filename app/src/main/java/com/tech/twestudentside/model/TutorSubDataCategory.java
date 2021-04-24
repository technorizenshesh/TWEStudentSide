package com.tech.twestudentside.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TutorSubDataCategory {
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_image")
    @Expose
    private String categoryImage;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    private String id;
    private boolean isChecked;
    private boolean isSelected;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sub_cat_name")
    @Expose
    private String subCatName;

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId2) {
        this.categoryId = categoryId2;
    }

    public String getSubCatName() {
        return this.subCatName;
    }

    public void setSubCatName(String subCatName2) {
        this.subCatName = subCatName2;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status2) {
        this.status = status2;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName2) {
        this.categoryName = categoryName2;
    }

    public String getCategoryImage() {
        return this.categoryImage;
    }

    public void setCategoryImage(String categoryImage2) {
        this.categoryImage = categoryImage2;
    }
}
