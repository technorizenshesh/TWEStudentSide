package com.tech.twestudentside;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import com.facebook.appevents.AppEventsConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tech.twestudentside.utils.Api;

public class Preference {
    public static final String APP_PREF = "KapsiePreferences";
    public static String KEYType_login = Api.social_login;
    public static String KEY_Address = "pic";
    public static String KEY_Address_id = "address_id";
    public static String KEY_CategoryId = "id";
    public static String KEY_DEsCriptionFinal = "DEsCriptionFinal";
    public static String KEY_FullCoursePrice = "Tutor_full_course";
    public static String KEY_Mobile_number = "number";
    public static String KEY_OrderDay = "OrderDay";
    public static String KEY_OrderTime = "OrderTime";
    public static String KEY_OrderiD = "name";
    public static String KEY_Ordertype = "Ordertype";
    public static String KEY_ReceiverId = "receiver_id";
    public static String KEY_SenderId = "sender_id";
    public static String KEY_Tutor_id = "Tutor_id";
    public static String KEY_Tutor_price_grp = "price_grp;";
    public static String KEY_Tutor_price_indivisual = "price_indvisua;";
    public static String KEY_USER_ID = "Student_id";
    public static String KEY_USER_email = "email";
    public static String KEY_ZipCode = "add";
    public static String KEY_location_id = FirebaseAnalytics.Param.LOCATION_ID;
    public static String KEY_location_name = "location_name";
    public static String KEY_reserve_type = "reserve_type";
    public static String KEY_start_date = FirebaseAnalytics.Param.START_DATE;
    public static String KEY_student_Name = "student_Name";
    public static String KEY_time_zone = "time_zone";
    public static String KEY_tutor_category_id = "tutor_category_id";
    public static String KEY_tutor_category_sub_id = "tutor_category_sub_id ";
    public static String key_Location_id = "locationId";
    public static String key_Pakage = "pakagee";
    public static String key_Pakage_price = "pakagePrice";
    public static String key_PlaceUser_address = "address_place";
    public static String key_Type = "type";

    /* renamed from: sp */
    public static SharedPreferences f412sp;
    private Activity activity;
    private Context context;

    public Preference(Activity activity2) {
        this.activity = activity2;
        this.context = activity2.getApplicationContext();
    }

    public boolean isNetworkAvailable() {
        return ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo() != null;
    }

    public static String get(Context context2, String key) {
        SharedPreferences sharedPreferences = context2.getSharedPreferences(APP_PREF, 0);
        f412sp = sharedPreferences;
        return sharedPreferences.getString(key, AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }

    public static void save(Context context2, String key, String value) {
        SharedPreferences sharedPreferences = context2.getSharedPreferences(APP_PREF, 0);
        f412sp = sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static void saveInt(Context context2, String key, int value) {
        SharedPreferences sharedPreferences = context2.getSharedPreferences(APP_PREF, 0);
        f412sp = sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public static void saveFloat(Context context2, String key, Float value) {
        SharedPreferences sharedPreferences = context2.getSharedPreferences(APP_PREF, 0);
        f412sp = sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putFloat(key, value.floatValue());
        edit.commit();
    }

    public static int getInt(Context context2, String key) {
        SharedPreferences sharedPreferences = context2.getSharedPreferences(APP_PREF, 0);
        f412sp = sharedPreferences;
        return sharedPreferences.getInt(key, 0);
    }

    public static Float getFloat(Context context2, String key) {
        SharedPreferences sharedPreferences = context2.getSharedPreferences(APP_PREF, 0);
        f412sp = sharedPreferences;
        return Float.valueOf(sharedPreferences.getFloat(key, 0.0f));
    }

    public static boolean saveBool(Context context2, String key, Boolean value) {
        SharedPreferences sharedPreferences = context2.getSharedPreferences(APP_PREF, 0);
        f412sp = sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key, value.booleanValue());
        edit.commit();
        return false;
    }

    public static Boolean getBool(Context context2, String key) {
        SharedPreferences sharedPreferences = context2.getSharedPreferences(APP_PREF, 0);
        f412sp = sharedPreferences;
        return Boolean.valueOf(sharedPreferences.getBoolean(key, false));
    }

    public static void clearPreference(Context context2) {
        SharedPreferences sharedPreferences = context2.getSharedPreferences(APP_PREF, 0);
        f412sp = sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
        edit.apply();
    }
}
