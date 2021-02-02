package com.tech.twestudentside.utils;

import com.tech.twestudentside.model.GetFavModel;
import com.tech.twestudentside.model.Get_detailsModel;
import com.tech.twestudentside.model.TutorCategory_Model;
import com.tech.twestudentside.model.TutorSubCategory;
import com.tech.twestudentside.model.TutorSubjectMode;
import com.tech.twestudentside.model.home_model;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {

    String signUp ="signup";
    String login ="login";
    String social_login ="social_login";
    String forgot_password ="forgot_password";
    String get_tutor ="get_tutor";
    String get_fav ="get_fav";
    String get_student_details ="get_details";
    String get_tutor_category ="get_tutor_category";
    String get_tutor_sub_category ="get_tutor_sub_category";
    String get_tutor_subject ="get_tutor_subject";
    String add_student_details ="add_student_details";
    String add_fav ="add_fav";

   @FormUrlEncoded
   @POST(login)
    Call<ResponseBody>login(
           @Field("email") String email,
           @Field("password") String password,
           @Field("type") String type,
           @Field("lat") String lat,
           @Field("lon") String lon,
           @Field("register_id") String register_id
   );

   @FormUrlEncoded
   @POST(social_login)
    Call<ResponseBody>Social_login(
           @Field("username") String username,
           @Field("email") String email,
           @Field("mobile") String mobile,
           @Field("type") String type,
           @Field("lat") String lat,
           @Field("lon") String lon,
           @Field("social_id") String social_id,
           @Field("register_id") String register_id
   );

   @FormUrlEncoded
   @POST(signUp)
    Call<ResponseBody>signUp(
           @Field("username") String username,
           @Field("email") String email,
           @Field("password") String password,
           @Field("mobile") String mobile,
           @Field("type") String type,
           @Field("lat") String lat,
           @Field("lon") String lon,
           @Field("register_id") String register_id
   );

   @FormUrlEncoded
   @POST(forgot_password)
    Call<ResponseBody>forgotPassword(
           @Field("email") String email
   );

    @FormUrlEncoded
    @POST(get_tutor)
    Call<home_model>get_tutor(
            @Field("search") String search,
            @Field("student_id") String student_id
    );

    @FormUrlEncoded
    @POST(get_fav)
    Call<GetFavModel>get_fav(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST(get_student_details)
    Call<Get_detailsModel>get_student_details(
            @Field("id") String id
    );


    @POST(get_tutor_category)
    Call<TutorCategory_Model> get_tutor_category();

    @FormUrlEncoded
    @POST(get_tutor_sub_category)
    Call<TutorSubCategory>get_tutor_sub_category(
            @Field("category_id") String category_id
    );

    @FormUrlEncoded
    @POST(get_tutor_subject)
    Call<TutorSubjectMode>get_tutor_subject(
            @Field("category_id") String category_id,
            @Field("sub_category_id") String sub_category_id
    );

    @FormUrlEncoded
    @POST(add_fav)
    Call<ResponseBody>add_fav(
            @Field("user_id") String user_id,
            @Field("tutor_id") String tutor_id
    );

/*
    @FormUrlEncoded
    @POST(add_student_details)
    Call<ResponseBody>add_student_details(
            @Field("student_id") String student_id,
            @Field("about") String about,
            @Field("dob") String dob,
            @Field("gender") String gender,
            @Field("where_to_teach") String where_to_teach,
            @Field("tutor_for") String tutor_for,
            @Field("profile_image") String profile_image
    );*/

    @Multipart
    @POST(add_student_details)
    Call<ResponseBody>add_student_details(
            @Part("student_id") RequestBody student_id,
            @Part("about") RequestBody about,
            @Part("dob") RequestBody dob,
            @Part("gender") RequestBody gender,
            @Part("where_to_teach") RequestBody where_to_teach,
            @Part("tutor_for") RequestBody tutor_for,
            @Part("category_id") RequestBody category_id,
            @Part("sub_category") RequestBody sub_category,
            @Part("subject") RequestBody subject,
            @Part("check_status") RequestBody check_status,
            @Part MultipartBody.Part part
    );

}
