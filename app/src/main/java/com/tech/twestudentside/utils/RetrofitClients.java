package com.tech.twestudentside.utils;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class RetrofitClients {

    private static final String BASE_URL ="https://triumph.cruvz.cloud/webservice/";
    private static RetrofitClients mInstance;
    private Retrofit retrofit;


    private RetrofitClients(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

public static synchronized RetrofitClients getInstance(){

        if (mInstance == null){
       mInstance = new RetrofitClients();
        }
        return mInstance;
    }

  public Api getApi(){
   return retrofit.create(Api.class);
  }

}
