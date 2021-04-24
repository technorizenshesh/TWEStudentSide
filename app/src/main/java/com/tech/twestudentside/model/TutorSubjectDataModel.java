package com.tech.twestudentside.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TutorSubjectDataModel {
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
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sub_category_id")
    @Expose
    private String subCategoryId;
    @SerializedName("sub_category_name")
    @Expose
    private String subCategoryName;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId2) {
        this.categoryId = categoryId2;
    }

    public String getSubCategoryId() {
        return this.subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId2) {
        this.subCategoryId = subCategoryId2;
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

    public String getSubCategoryName() {
        return this.subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName2) {
        this.subCategoryName = subCategoryName2;
    }
}
