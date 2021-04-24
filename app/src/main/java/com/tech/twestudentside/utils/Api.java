package com.tech.twestudentside.utils;

import com.tech.twestudentside.model.ChatConversation;
import com.tech.twestudentside.model.GetCalculaterModel;
import com.tech.twestudentside.model.GetChat;
import com.tech.twestudentside.model.GetFavModel;
import com.tech.twestudentside.model.Get_detailsModel;
import com.tech.twestudentside.model.GteTutorTime;
import com.tech.twestudentside.model.RelatedModel;
import com.tech.twestudentside.model.SearchModel;
import com.tech.twestudentside.model.TutorCategory_Model;
import com.tech.twestudentside.model.TutorSubCategory;
import com.tech.twestudentside.model.TutorSubjectMode;
import com.tech.twestudentside.model.getAddress;
import com.tech.twestudentside.model.home_model;
import com.tech.twestudentside.model.myprofile_model;
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
     String add_address_onw = "add_address";
     String add_fav = "add_fav";
     String add_student_details = "add_student_details";
     String cashfree_signature = "cashfree_signature";
     String forgot_password = "forgot_password";
     String get_address = "get_address";
     String get_chat = "get_chat";
     String get_conversation_detail = "get_conversation_detail";
     String get_fav = "get_fav";
     String get_profile = "get_profile";
     String get_search_tutor = "search_tutor";
     String get_student_details = "get_details";
     String get_tutor = "get_tutor";
     String get_tutor_category = "get_tutor_category";
     String get_tutor_package = "get_tutor_package";
     String get_tutor_sub_category = "get_tutor_sub_category";
     String get_tutor_subject = "get_tutor_subject";
     String get_tutor_time = "get_tutor_time";
     String insert_chat = "insert_chat";
     String login = "login";
     String near_tutor = "near_tutor";
     String related_tutor = "related_tutor";
     String signUp = "signup";
     String social_login = "social_login";
     String tutor_booking = "tutor_booking";
     String contact_info = "contact_info";
     String privacy_policy = "privacy_policy";
     String terms_conditions ="terms_conditions";

    @FormUrlEncoded
    @POST("social_login")
    Call<ResponseBody> Social_login(@Field("username") String str, @Field("email") String str2, @Field("mobile") String str3, @Field("type") String str4, @Field("lat") String str5, @Field("lon") String str6, @Field("social_id") String str7, @Field("register_id") String str8);

    @FormUrlEncoded
    @POST("add_address")
    Call<ResponseBody> add_address(@Field("user_id") String str, @Field("address_type") String str2, @Field("address") String str3, @Field("lat") String str4, @Field("lon") String str5);

    @FormUrlEncoded
    @POST("add_fav")
    Call<ResponseBody> add_fav(@Field("user_id") String str, @Field("tutor_id") String str2);

    @POST("add_student_details")
    @Multipart
    Call<ResponseBody> add_student_details(@Part("student_id") RequestBody requestBody, @Part("about") RequestBody requestBody2, @Part("dob") RequestBody requestBody3, @Part("gender") RequestBody requestBody4, @Part("where_to_teach") RequestBody requestBody5, @Part("tutor_for") RequestBody requestBody6, @Part("category_id") RequestBody requestBody7, @Part("sub_category") RequestBody requestBody8, @Part("subject") RequestBody requestBody9, @Part("check_status") RequestBody requestBody10, @Part MultipartBody.Part part);

    @FormUrlEncoded
    @POST("cashfree_signature")
    Call<ResponseBody> cashfree_signature(@Field("order_id") String str, @Field("total_amount") String str2);

    @FormUrlEncoded
    @POST("forgot_password")
    Call<ResponseBody> forgotPassword(@Field("email") String str);

    @FormUrlEncoded
    @POST("get_address")
    Call<getAddress> get_address(@Field("user_id") String str);

    @FormUrlEncoded
    @POST("get_chat")
    Call<GetChat> get_chat(@Field("sender_id") String str, @Field("receiver_id") String str2);

    @FormUrlEncoded
    @POST("get_conversation_detail")
    Call<ChatConversation> get_conversation_detail(@Field("receiver_id") String str);

    @FormUrlEncoded
    @POST("get_fav")
    Call<GetFavModel> get_fav(@Field("user_id") String str);

    @FormUrlEncoded
    @POST("get_profile")
    Call<myprofile_model> get_profile(@Field("user_id") String str);

    @FormUrlEncoded
    @POST("search_tutor")
    Call<SearchModel> get_search_tutor(@Field("name") String str);

    @FormUrlEncoded
    @POST("get_details")
    Call<Get_detailsModel> get_student_details(@Field("id") String str);

    @FormUrlEncoded
    @POST("get_tutor")
    Call<home_model> get_tutor(@Field("search") String str, @Field("student_id") String str2);

    @POST("get_tutor_category")
    Call<TutorCategory_Model> get_tutor_category();

    @FormUrlEncoded
    @POST("get_tutor_package")
    Call<GetCalculaterModel> get_tutor_package(@Field("tutor_id") String str, @Field("type") String str2);

    @FormUrlEncoded
    @POST("get_tutor_sub_category")
    Call<TutorSubCategory> get_tutor_sub_category(@Field("category_id") String str);

    @FormUrlEncoded
    @POST("get_tutor_subject")
    Call<TutorSubjectMode> get_tutor_subject(@Field("category_id") String str, @Field("sub_category_id") String str2);

    @FormUrlEncoded
    @POST("get_tutor_time")
    Call<GteTutorTime> get_tutor_time(@Field("tutor_id") String str);

    @FormUrlEncoded
    @POST("insert_chat")
    Call<ResponseBody> insert_chat(@Field("sender_id") String str, @Field("receiver_id") String str2, @Field("chat_message") String str3);

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> login(@Field("email") String str, @Field("password") String str2, @Field("type") String str3, @Field("lat") String str4, @Field("lon") String str5, @Field("register_id") String str6);

   /* @FormUrlEncoded
    @POST("near_tutor")
    Call<MapModel> near_tutor(@Field("lat") String str, @Field("lon") String str2, @Field("user_id") String str3);
*/
    @FormUrlEncoded
    @POST("related_tutor")
    Call<RelatedModel> related_tutor(@Field("user_id") String str, @Field("lat") String str2, @Field("lon") String str3);

    @FormUrlEncoded
    @POST("signup")
    Call<ResponseBody> signUp(@Field("username") String str, @Field("email") String str2, @Field("password") String str3, @Field("mobile") String str4, @Field("type") String str5, @Field("lat") String str6, @Field("lon") String str7, @Field("register_id") String str8);


    @FormUrlEncoded
    @POST(contact_info)
    Call<ResponseBody>contact_info(
            @Field("user_id") String user_id,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("message") String message
    );

    @POST(privacy_policy)
    Call<ResponseBody> privacy_policy();


    @POST(terms_conditions)
    Call<ResponseBody> terms_conditions();

    @FormUrlEncoded
    @POST("tutor_booking")
    Call<ResponseBody> tutor_booking(@Field("tutor_id") String str, @Field("student_id") String str2, @Field("reserve_type") String str3, @Field("monday") String str4, @Field("tuesday") String str5, @Field("wednesday") String str6, @Field("thursday") String str7, @Field("friday") String str8, @Field("saturday") String str9, @Field("sunday") String str10, @Field("timezone") String str11, @Field("startdate") String str12, @Field("pakage_type") String str13, @Field("totalprice") String str14, @Field("payment_type") String str15);

}
