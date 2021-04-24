package com.tech.twestudentside.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TutorCategoryModel {
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    private String id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("status")
    @Expose
    private String status;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName2) {
        this.categoryName = categoryName2;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image2) {
        this.image = image2;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status2) {
        this.status = status2;
    }
}
